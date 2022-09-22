package edu.wit.hbasemanager.controller;

import edu.wit.hbasemanager.model.ConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db")
public class ConnectionController {
    @GetMapping("getConnection/{ip}")
    public Connection getConnection(@PathVariable("ip") String ip) {
        return ConnectionManager.getConnection(ip);
    }
    @GetMapping("closeConnection/{ip}")
    public boolean closeConnection(@PathVariable("ip") String ip) {
        return ConnectionManager.closeConnection(ip);
    }
    @PostMapping("createConnection")
    public Connection CreateConnection(String ip, Configuration config) {
        return ConnectionManager.createConnection(ip,config);
    }
}
