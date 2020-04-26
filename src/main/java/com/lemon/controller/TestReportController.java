package com.lemon.controller;


import com.lemon.common.Result;
import com.lemon.service.TestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@RestController
@RequestMapping("/testReport")
public class TestReportController {

    @Autowired
    TestReportService testReportService;
    @RequestMapping("/run")
    public Result run(Integer suiteId){
        testReportService.run(suiteId);
        Result result = new Result("1","运行成功");
        return result;
    }

}
