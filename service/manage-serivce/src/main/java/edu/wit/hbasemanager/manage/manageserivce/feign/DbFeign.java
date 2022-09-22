package edu.wit.hbasemanager.manage.manageserivce.feign;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="db-service")
@Component
public interface DbFeign {
    @GetMapping("getConnection/{ip}")
    Connection getConnection(@PathVariable("ip") String ip);
    @GetMapping("closeConnection/{ip}")
    boolean closeConnection(@PathVariable("ip") String ip);
    @PostMapping("createConnection")
    Connection CreateConnection(String ip, Configuration config);
}
