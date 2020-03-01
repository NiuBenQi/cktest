package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.Result;
import com.lemon.pojo.User;
import com.lemon.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */

@RestController
@Api("用户模块")
@RequestMapping("/user")
//@CrossOrigin
public class UserController {
    // 按类型注入 Autowired

    @Autowired
    private UserService userService;

    //    @RequestMapping("/register")
    @PostMapping("/register")
    @ApiOperation(value = "注册方法", httpMethod = "POST")
    public Result register(User user) throws ParseException {
        //调用业务层方法，插入到DB，统一处理异常
        userService.save(user);
        Result result = new Result("1", "注册成功");
        return result;
    }

    @GetMapping("/find")
    @ApiOperation(value = "账号验重方法", httpMethod = "GET")
    public Result find(User user) {
        // 调用业务层方法，查询DB 非主键列，统一处理异常
        Result result = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
        User response = userService.getOne(queryWrapper);
        if (response == null) {
            result = new Result("1", "用户账号不存在");
        } else {
            result = new Result("0", "用户账号已存在");
        }
        return result;
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录方法", httpMethod = "POST")
    public Result login(User user) {
        // （原始方法）调用业务层方法，查询DB，根据username-user，判断password
        Result result = null;
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //shiro
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            // 讲session返回去
            String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
            User loginUser =(User) subject.getPrincipal();
            result = new Result("1", loginUser.getId(), sessionId);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                result = new Result("0", "用户名错误");
            } else {
                result = new Result("0", "密码错误");
            }
            e.printStackTrace();
        }
        return result;
    }


    @GetMapping("/logout")
    @ApiOperation(value = "退出方法", httpMethod = "GET")
    public Result logout() {
        Result result = null;
        SecurityUtils.getSubject().logout();
        result = new Result("1","账号未登录");
        return result;
    }

    @GetMapping("/unauth")
    @ApiOperation(value = "未授权方法", httpMethod = "GET")
    public Result unauth() {
        Result result = null;
        // 从shiro退出会话
        result = new Result("1","账号未登录");
        return result;
    }

//    @GetMapping("/center")
//    @ApiOperation(value = "查看用户中心方法", httpMethod = "GET")
//    public Result center(Integer userid) {
//        Result result = null;
//        String principal =(String) SecurityUtils.getSubject().getPrincipal();
//        SecurityUtils.getSubject().
//        result = new Result("100",principal,"查询成功");
//        System.out.println(result);
//        return result;
//    }

}
