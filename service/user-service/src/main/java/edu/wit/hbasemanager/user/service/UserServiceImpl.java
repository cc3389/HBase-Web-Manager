package edu.wit.hbasemanager.user.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.wit.hbasemanager.common.jwt.JwtHelper;
import edu.wit.hbasemanager.model.LoginVo;
import edu.wit.hbasemanager.user.entity.User;
import edu.wit.hbasemanager.user.mapper.UserMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 1.查询redis，判断是否正确
     * 2.如果正确 查询数据库判断用户是否存在
     * 3.不存在用户则新建用户
     * @param email
     * @param code
     * @return
     */
    @Override
    public LoginVo LoginService(String email, String code) {
        String redisCode = (String)redisTemplate.opsForValue().get(email + ":" + "verify");
        LoginVo loginVo = null;
        if (redisCode==code) {
            Wrapper<User> wrapper = new QueryWrapper<User>().eq("email",email);
            User user = baseMapper.selectOne(wrapper);
            if (user==null) {
                //用户不存在时，新建账号
                user = new User();
                user.setAdmin(false);
                user.setEmail(email);
                baseMapper.insert(user);
                user = baseMapper.selectOne(wrapper);
            }
            // 验证成功，生成token
            String token = JwtHelper.createToken(user.getUserId(), email, user.isAdmin() ? "admin" : "user");
            redisTemplate.opsForValue().set(email+":"+"token",token);
            redisTemplate.expire(email+":"+"token",24*60*60*1000, TimeUnit.MILLISECONDS);
            loginVo = new LoginVo();
            loginVo.setEmail(email);
            loginVo.setRole(user.isAdmin() ? "admin" : "user");
            loginVo.setToken(token);
            return loginVo;
        } else {
            //redis验证失败
            return loginVo;
        }



    }
}
