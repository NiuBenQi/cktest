package com.lemon.controller;


import com.lemon.common.ApiVO;
import com.lemon.common.CaseEditVO;
import com.lemon.common.CaseListVO;
import com.lemon.common.Result;
import com.lemon.pojo.ApiRequestParam;
import com.lemon.pojo.CaseParamValue;
import com.lemon.pojo.Cases;
import com.lemon.service.CaseParamValueService;
import com.lemon.service.CasesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/cases")
public class CasesController {

    @Autowired
    CasesService casesService;

    @PostMapping("/add")
    @ApiOperation(value = "添加到case", httpMethod = "POST")
    public Result add(Cases caseVo, ApiVO apiRunVo) {
        // 添加到case
        casesService.add(caseVo, apiRunVo);
        return new Result("1", "添加到测试集成功");
    }


    @GetMapping("/showCaseUnderProject")
    @ApiOperation(value = "根据项目id查询测试套件", httpMethod = "GET")
    public Result showCaseUnderProject(Integer projectId) {
        // 添加到case
        List<CaseListVO> list = casesService.showCaseUnderProject(projectId);
        return new Result("1", list, "根据projectId查询测试套件成功");
    }

    @GetMapping("/showCaseUnderSuite")
    @ApiOperation(value = "根据套件id查询测试用例", httpMethod = "GET")
    public Result showCaseUnderSuite(String suiteId) {
        // 添加到case
        List<CaseListVO> list = casesService.showCaseUnderSuite(suiteId);
        return new Result("1", list, "根据套件id查询测试用例成功");
    }

    @GetMapping("/toCaseEdit")
    @ApiOperation(value = "根据caseId查询测试数据", httpMethod = "GET")
    public Result toCaseEdit(String caseId) {
        CaseEditVO list = casesService.findCaseEditVo(caseId);
        return new Result("1", list, "查询测试数据成功");
    }
}
