package edu.wit.hbasemanager.manage.manageservice.controller;


import edu.wit.hbasemanager.common.result.Result;
import edu.wit.hbasemanager.manage.manageservice.HBaseTool.HBaseDDL;
import edu.wit.hbasemanager.manage.manageservice.feign.DbFeignClient;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-09-25
 */
@RestController
@RequestMapping("/hbaseManage")
public class HbaseManageController {
    @Autowired
    DbFeignClient dbClient;

    /**
     * @Author Shawn Yue
     * @Description // 删除命名空间
     * @Date 15:58 2022/9/27
     * @Param [sourceName, namespace]
     **/
    @GetMapping("/auth/delNamespace")
    public Result delNamespace(@RequestParam("sourceName") String sourceName,
                               @RequestParam("namespace") String namespace) throws IOException {
        Connection connection = dbClient.getConnection(sourceName);
        HBaseDDL.delNamespace(namespace, connection);
        return Result.ok();
    }

}

