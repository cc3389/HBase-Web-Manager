package edu.wit.hbasemanager.user.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
public class User {
    long userId;
    String email;
    boolean admin;
}
