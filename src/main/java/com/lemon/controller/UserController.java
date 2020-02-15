package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.Result;
import com.lemon.pojo.User;
import com.lemon.service.UserService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;


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
@CrossOrigin
public class UserController {
    // 按类型注入 Autowired

    @Autowired
    private UserService userService;

    // 注册方法
    @PostMapping("/register")
    @ApiOperation(value = "注册方法", httpMethod = "POST")
    public Result register(User user) {
        //调用业务层方法，插入到DB，统一处理异常
        userService.save(user);
        Result result = new Result("1", "注册成功");
        return result;
    }

    // 账号验重方法
    @GetMapping("/find")
    @ApiOperation(value = "账号验重方法", httpMethod = "GET")
    public Result find(User username) {
        // 调用业务层方法，查询DB 非主键列，统一处理异常
        Result result = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            new Result("0", "用户账号不存在");

        } else {
            new Result("1", "用户账号已存在");
        }
        return result;
    }

}
