package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.ApiClassificationVO;
import com.lemon.common.Result;
import com.lemon.pojo.Api;
import com.lemon.pojo.ApiClassification;
import com.lemon.pojo.User;
import com.lemon.service.ApiClassificationService;
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

    // 根据projectId单表查询分类信息
    @GetMapping("/findAll")
    @ApiOperation(value = "根据projectId单表查询分类信息", httpMethod = "GET")
    public Result findAll (Integer projectId) {
        Result result = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id",projectId);
        List<ApiClassification> list = apiClassificationService.list(queryWrapper);
        result = new Result("1",list, "查询成功");
        return result;
    }

    @PostMapping("/add")
    public Result addapiClassification(String name,String description,Integer projectId) {
        ApiClassification apiClassification = new ApiClassification();
        apiClassification.setName(name);
        apiClassification.setDescription(description);
        apiClassification.setProjectId(projectId);
        User user =(User) SecurityUtils.getSubject().getPrincipal();
        apiClassification.setCreateUser(user.getId());
        apiClassification.setCreateTime(new Date());
        apiClassificationService.save(apiClassification);
        Result result = new Result("1","接口分类添加成功");
        return result;
    }
}
