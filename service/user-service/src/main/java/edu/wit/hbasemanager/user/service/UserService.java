package edu.wit.hbasemanager.user.service;

import edu.wit.hbasemanager.model.LoginVo;

public interface UserService {
   LoginVo loginService(String email, String code);
   String logoutService(String email);
   Boolean blackListService(String mail);

   void sendCodeService(String email, String code);
}
