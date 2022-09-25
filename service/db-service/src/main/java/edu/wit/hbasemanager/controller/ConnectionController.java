package edu.wit.hbasemanager.controller;

import edu.wit.hbasemanager.model.ConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db/connection/inner/")
public class ConnectionController {
    @GetMapping("getConnection/{name}")
    public Connection getConnection(@PathVariable("name") String name) {
        return ConnectionManager.getConnection(name);
    }
    @GetMapping("closeConnection/{name}")
    public boolean closeConnection(@PathVariable("name") String name) {
        return ConnectionManager.closeConnection(name);
    }
    @PostMapping("createConnection")
    public Connection CreateConnection(String name, Configuration config) {
        return ConnectionManager.createConnection(name,config);
    }
}
