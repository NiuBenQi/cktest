package com.lemon.controller;


import com.lemon.common.ReportVO;
import com.lemon.common.Result;
import com.lemon.pojo.TestReport;
import com.lemon.service.TestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<TestReport> testReportList = testReportService.run(suiteId);
        Result result = new Result("1",testReportList,"测试执行成功");
        return result;
    }

    @RequestMapping("/findCaseRunResult")
    public Result findCaseRunResult(Integer caseId){
        TestReport testReport = testReportService.findByCaseId(caseId);
        Result result = new Result("1",testReport,"测试执行成功");
        return result;
    }

    @GetMapping("/get")
    public Result get(Integer suiteId){
        ReportVO report = testReportService.getReport(suiteId);
        Result result = new Result("1",report,"测试执行成功");
        return result;
    }
}
