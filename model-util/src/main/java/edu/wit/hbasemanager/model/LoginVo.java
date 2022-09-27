package edu.wit.hbasemanager.model;

import lombok.Data;

@Data
public class LoginVo {
    private String role;
    private String email;
    private String token;
}
