package edu.wit.hbasemanager.user.controller;

import edu.wit.hbasemanager.common.code.VerfiyCodeUtil;
import edu.wit.hbasemanager.common.jwt.JwtHelper;
import edu.wit.hbasemanager.common.result.Result;
import edu.wit.hbasemanager.model.LoginVo;
import edu.wit.hbasemanager.model.MailMessage;
import edu.wit.hbasemanager.user.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 用户登录
     * @param email 邮箱
     * @param code 验证码
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestParam("email") String email,@RequestParam("code") String code) {
        if (email==null || code==null) {
            //错误
            return Result.fail();
        }
        LoginVo loginVo = userService.loginService(email, code);
        if (loginVo!=null) {
            return Result.ok(loginVo);
        } else {
            return Result.fail();
        }

    }

    /**
     * 用户登出
     * @param email
     * @return
     */
    @PostMapping("/auth/logout")
    public Result logout(@RequestHeader("email") String email) {
        // TODO
        if (email==null) {
            //错误
            return Result.fail();
        }
        userService.logoutService(email);
        return Result.ok();
    }

    /**
     *  黑名单
     * @param email
     * @return
     */
    @PostMapping("auth/blackList")
    public Result blackList(@RequestParam("email") String email) {
        if (email==null) {
            return Result.fail();
        }
        boolean ban = userService.blackListService(email);
        if (ban) {
            return Result.build(200,"用户已禁用");
        } else {
            return Result.build(200,"用户已启用");
        }

    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    public Result sendCode(@RequestParam("email") String email) {
        //生成6位验证码，放入redis,
        String code = VerfiyCodeUtil.generateCode();
        userService.sendCodeService(email,code);
        return Result.ok();
    }
}
