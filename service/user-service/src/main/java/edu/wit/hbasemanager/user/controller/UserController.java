package edu.wit.hbasemanager.user.controller;

import edu.wit.hbasemanager.common.result.Result;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /**
     *
     * @param email 邮箱
     * @param code 验证码
     * @return Result
     */
    public Result login(String email,String code) {
        // TODO
    }
    public Result logout() {
        // TODO
    }
}
