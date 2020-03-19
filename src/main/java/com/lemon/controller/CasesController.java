package com.lemon.controller;


import com.lemon.common.ApiVO;
import com.lemon.common.Result;
import com.lemon.pojo.ApiRequestParam;
import com.lemon.pojo.CaseParamValue;
import com.lemon.pojo.Cases;
import com.lemon.service.CaseParamValueService;
import com.lemon.service.CasesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/cases")
public class CasesController {

    @Autowired
    CasesService casesService;

    @PostMapping("/add")
    @ApiOperation(value = "添加到case",httpMethod = "POST")
    public Result add(Cases caseVo, ApiVO apiRunVo){
        // 添加到case
        casesService.add(caseVo,apiRunVo);
        return new Result("1","添加到测试集成功");
    }
}
