package com.lemon.controller;


import com.lemon.common.ApiClassificationVO;
import com.lemon.common.Result;
import com.lemon.service.ApiClassificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/apiClassification")
public class ApiClassificationController {

    @Autowired
    ApiClassificationService apiClassificationService;

    @GetMapping("/toIndex")
    @ApiOperation(value = "根据projectid查询分类信息", httpMethod = "GET")
    public Result getWithApi(Integer projectId, Integer tab) {
        Result result = null;
        if (tab == 1) {
            // 接口列表
            List<ApiClassificationVO> list = apiClassificationService.getWithApi(projectId);
            result = new Result("1",list,"查询分类同时也延迟加载api");
        } else if (tab == 2) {
            // 测试集合
        }
        return result;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据apiClassification.id删除分类信息", httpMethod = "GET")
    public Result delClassification (@PathVariable("id") Integer id) {
        Result result = null;
        apiClassificationService.removeById(id);
        result = new Result("1", "删除成功");
        return result;
    }
}
