package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.Result;
import com.lemon.service.SuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public Result findAll(Integer projectId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id",projectId);
        suiteService.list(queryWrapper);
        return new Result("1","成功");
    }
}
