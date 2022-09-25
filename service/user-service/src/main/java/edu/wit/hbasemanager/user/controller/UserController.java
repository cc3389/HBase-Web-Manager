package edu.wit.hbasemanager.user.controller;

import edu.wit.hbasemanager.common.jwt.JwtHelper;
import edu.wit.hbasemanager.common.result.Result;
import edu.wit.hbasemanager.model.LoginVo;
import edu.wit.hbasemanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    UserService userService;
    /**
     *
     * @param email 邮箱
     * @param code 验证码
     * @return Result
     */
    @PostMapping("/login")
    public Result login(String email,String code) {
        if (email==null || code==null) {
            //错误
            return Result.fail();
        }
        LoginVo loginVo = userService.LoginService(email, code);
        if (loginVo!=null) {
            return Result.ok(loginVo);
        } else {
            return Result.fail();
        }

    }
    public Result logout() {
        // TODO
        return Result.fail();
    }
}
