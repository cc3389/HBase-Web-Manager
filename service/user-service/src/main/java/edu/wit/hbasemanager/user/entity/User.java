package edu.wit.hbasemanager.user.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
public class User {
    private long userId;
    private String email;
    private boolean admin;
}
