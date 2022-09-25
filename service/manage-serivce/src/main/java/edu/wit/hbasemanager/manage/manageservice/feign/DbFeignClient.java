package edu.wit.hbasemanager.manage.manageservice.feign;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="db-service",path = "db/connection/inner/")
@Component
public interface DbFeignClient {
    @GetMapping("getConnection/{name}")
    Connection getConnection(@PathVariable("name") String name);
    @GetMapping("closeConnection/{name}")
    boolean closeConnection(@PathVariable("name") String name);
    @PostMapping("createConnection")
    Connection CreateConnection(String name, Configuration config);

}
