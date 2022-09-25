package edu.wit.hbasemanager.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cc3389
 * @date 2022/09/09 11:05
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("edu.wit.hbasemanager.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
