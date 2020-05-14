package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.ApiVO;
import com.lemon.common.Result;
import com.lemon.pojo.Cases;
import com.lemon.pojo.Suite;
import com.lemon.pojo.User;
import com.lemon.service.SuiteService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@RestController
@RequestMapping("/suite")
public class SuiteController {
    @Autowired
    SuiteService suiteService;

    @GetMapping("/listAll")
    @ApiOperation(value = "根据projectId获取suite方法", httpMethod = "GET")
    public Result findAll(Integer projectId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id",projectId);
        List<Suite> list = suiteService.list(queryWrapper);
        return new Result("1",list,"成功");
    }


    @PostMapping("/add")
    @ApiOperation(value = "添加测试套件", httpMethod = "POST")
    public Result add(Suite suite) {
        User user =(User) SecurityUtils.getSubject().getPrincipal();
        suite.setCreateUser(user.getId());
        suite.setCreateTime(new Date());
        System.out.println("suite值为："+suite);
        suiteService.save(suite);

        return new Result("1","成功");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据suiteId删除测试套件", httpMethod = "GET")
    public Result delsuite(@PathVariable("id") Integer suiteId) {
        if(suiteId == null ){
            return new Result("999","suiteId 不能为空");
        }
        suiteService.removeById(suiteId);
        return new Result("1","删除测试套件成功");
    }

}
