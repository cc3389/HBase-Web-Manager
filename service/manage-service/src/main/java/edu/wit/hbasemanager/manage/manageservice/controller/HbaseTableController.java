package edu.wit.hbasemanager.manage.manageservice.controller;

import edu.wit.hbasemanager.common.result.Result;
import edu.wit.hbasemanager.manage.manageservice.HBaseTool.HBaseDDL;
import edu.wit.hbasemanager.manage.manageservice.HBaseTool.HBaseDML;
import edu.wit.hbasemanager.manage.manageservice.entity.HbaseDataSourceConfiguration;
import edu.wit.hbasemanager.manage.manageservice.entity.HbaseManage;
import edu.wit.hbasemanager.manage.manageservice.feign.DbFeignClient;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseDataSourceConfigurationService;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseManageService;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseUserService;
import edu.wit.hbasemanager.model.GetTableVo;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

/**
 * @Classname HbaseTableController
 * @Description TODO
 * @Author Shawn Yue
 * @Date 18:18
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/HbaseTable")
public class HbaseTableController {
    @Autowired
    DbFeignClient dbClient;
    @Autowired
    HbaseManageService hbaseManageService;
    @Autowired
    HbaseDataSourceConfigurationService hdsService;

    /**
     * @Author Shawn Yue
     * @Description // 创建hbase新表
     *              // 根据userid查询mysql找到数据源名称数组,
     *              // 与传入的namespace进行比对查询是否一致，
     *              // 若一致则新建表
     * @Date 15:59 2022/9/27
     * @Param [tableName, family, userId, namespace, sourceName]
     **/
    @PostMapping("/auth/createTable")
    public Result createTable(@RequestParam("tableName") String tableName,
                              @RequestParam("family") String[] family,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("namespace") String namespace,
                              @RequestParam("sourceName") String sourceName) throws IOException {
        List<HbaseManage> hbaseManages = hbaseManageService.selectByUserId(userId);
        String s = null;
        for(HbaseManage v : hbaseManages){
            s = hdsService.selectDataNameByDataId(v.getDatasourceId());
            if(s.equals(namespace)){
                Connection connection = dbClient.getConnection(sourceName);
                HBaseDDL.createTable(namespace, tableName, family, connection);     // 创建表
                return Result.ok();
            }
        }
        return Result.fail();
    }

    /**
     * @Author Shawn Yue
     * @Description // 根据命名空间查询所有表
     * @Date 20:44 2022/9/26
     * @Param [sourceName, namespace]
     * @return
     **/
    @GetMapping("/getTableName")
    public Result getTableName(@RequestParam("sourceName") String sourceName,
                               @RequestParam("namespace") String namespace) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        List<String> tableName = HBaseDDL.getTableName(connection, namespace);
        return Result.ok(tableName);
    }

    /**
     * @Author Shawn Yue
     * @Description // 显示表中所有数据
     *              // start默认为0 stop默认为10
     * @Date 21:00 2022/9/26
     * @Param [sourceName, namespace, startRow, stopRow, tableName]
     * @return
     **/
    @GetMapping("/getTable")
    public Result getTable(@RequestParam("sourceName") String sourceName,
                           @RequestParam("namespace") String namespace,
                           @RequestParam("startRow") Integer startRow,
                           @RequestParam("stopRow") Integer stopRow,
                           @RequestParam("tableName") String tableName) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        List<GetTableVo> cells = HBaseDML.scanRows(namespace, tableName, startRow, stopRow, connection);
        return Result.ok(cells);
    }

    /**
     * @Author Shawn Yue
     * @Description // 向表中插入数据
     * @Date 11:43 2022/9/27
     * @Param [sourceName, namespace, tableName, rowKey, columnFamily, columnName, value]
     * @return
     **/
    @PostMapping("/insertValue")
    public Result insertValue(@RequestParam("sourceName") String sourceName,
                              @RequestParam("namespace") String namespace,
                              @RequestParam("tableName") String tableName,
                              @RequestParam("rowKey") Integer rowKey,
                              @RequestParam("columnFamily") String columnFamily,
                              @RequestParam("columnName") String columnName,
                              @RequestParam("value") String value) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        HBaseDML.putCell(namespace, tableName, rowKey, columnFamily, columnName, value, connection);
        return Result.ok();
    }

    /**
     * @Author Shawn Yue
     * @Description // 删除一行数据
     * @Date 15:54 2022/9/27
     * @Param [sourceName, namespace, tableName, rowKey]
     **/
    @GetMapping("/delRowData")
    public Result delRowData(@RequestParam("sourceName") String sourceName,
                             @RequestParam("namespace") String namespace,
                             @RequestParam("tableName") String tableName,
                             @RequestParam("rowKey") Integer rowKey) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        HBaseDML.deleteRow(namespace, tableName, rowKey, connection);
        return Result.ok();
    }
    /**
     * @Author fangkun
     * @Description // 删除一张hbase表
     * @Date 15:53 2022/9/27
     * @Param [sourceName, namespace, tableName]
     **/
    @PostMapping("/auth/delTable")
    public Result delTable(@RequestParam("sourceName") String sourceName,
                           @RequestParam("namespace") String namespace,
                           @RequestParam("tableName") String tableName) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        HBaseDDL.deleteTable(namespace, tableName, connection);
        return Result.ok();
    }
}
