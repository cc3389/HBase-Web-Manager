package edu.wit.hbasemanager.user.service;

import edu.wit.hbasemanager.model.LoginVo;
import edu.wit.hbasemanager.user.entity.User;

public interface UserService {
   LoginVo LoginService(String email, String code);
}
