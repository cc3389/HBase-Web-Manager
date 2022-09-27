package edu.wit.hbasemanager.manage.manageservice.controller;


import edu.wit.hbasemanager.common.result.Result;
import edu.wit.hbasemanager.manage.manageservice.HBaseTool.HBaseDDL;
import edu.wit.hbasemanager.manage.manageservice.HBaseTool.HBaseDML;
import edu.wit.hbasemanager.manage.manageservice.feign.DbFeignClient;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseDataSourceConfigurationService;
import edu.wit.hbasemanager.manage.manageservice.service.HbaseManageService;
import edu.wit.hbasemanager.manage.manageservice.serviceImpl.HbaseManageServiceImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Shawn Yue
 * @Description //TODO Shawn Yue
 * @Date 18:04 2022/9/26
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/api/hbaseDataSourceConfiguration")
public class HbaseDataSourceConfigurationController {
    @Autowired
    HbaseManageService hbaseManageService;
    @Autowired
    HbaseDataSourceConfigurationService hdsService;
    @Autowired
    DbFeignClient dbClient;

    /**
     * @Author Shawn Yue
     * @Description // 创建新的数据源
     *              // 在mysql管理表中添加新的数据源信息
     *              // 更新mysql数据源表
     * @Date 15:56 2022/9/27
     * @Param [sourceName, configMap]
     **/
    @PostMapping("/auth/createDataConfig")
    public Result createDataConfig(@RequestParam("sourceName") String sourceName,
                                   @RequestParam("configMap") HashMap<String, String> configMap){
        Configuration configuration = new Configuration();
        for (Map.Entry<String,String> e: configMap.entrySet()) {
            configuration.set(e.getKey(), e.getValue());                // 配置多个ip
        }
        dbClient.CreateConnection(sourceName, configuration);
        return Result.ok();
    }

    /**
     * @Author Shawn Yue
     * @Description // 创建新的命名空间
     *              // 在hbase中创建新的命名空间
     * @Date 15:57 2022/9/27
     * @Param [namespace, sourceName]
     **/
    @PostMapping("/auth/createNamespace")
    public Result createNamespace(@RequestParam("namespace") String namespace,
                                  @RequestParam("sourceName") String sourceName) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        HBaseDDL.createNamespace(namespace, connection); // 2.2 给命令空间添加需求 builder.addConfiguration("user","hadoop");
        return Result.ok();
    }

    /**
     * @Author Shawn Yue
     * @Description // 根据数据源打印所有命名空间
     *              // 根据dataId查询到所有命名空间
     * @Date 19:42 2022/9/26
     * @param dataId
     * @return
     **/
    @GetMapping("/getNamespace")
    public Result getNamespace(@RequestParam("dataId") Integer dataId) throws IOException {
        String dataName = hdsService.selectDataNameByDataId(dataId);
        Connection connection = dbClient.getConnection(dataName);
        String[] namespace = HBaseDDL.getNamespace(connection);
        return Result.ok(namespace);
    }

    /**
     * @Author Shawn Yue
     * @Description // 删除数据源
     *              // 先删除hbase中的数据源
     *              // 再删除mysql中管理表、数据源表的数据
     * @Date 15:34 2022/9/27
     * @param sourceName
     * @return
     **/
    @PostMapping("/auth/delSourceData")
    public Result delSourceData(@RequestParam("sourceName") String sourceName){
        dbClient.closeConnection(sourceName);
        Integer id = hdsService.selectDataIdByName(sourceName);
        hdsService.delSourceData(id);
        hbaseManageService.delSourceData(id);
        return Result.ok();
    }
}

