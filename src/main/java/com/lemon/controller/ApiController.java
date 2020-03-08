package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.ApiListVO;
import com.lemon.common.ApiVO;
import com.lemon.common.Result;
import com.lemon.pojo.Api;
import com.lemon.pojo.User;
import com.lemon.service.ApiRequestParamService;
import com.lemon.service.ApiService;
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
@RequestMapping("/api")
public class ApiController {
    @Autowired
    ApiService apiService;
    @Autowired
    ApiRequestParamService apiRequestParamService;

    @GetMapping("/showApiUnderProject")
    public Result showApiListByProject(Integer projectId) {

        List<ApiListVO> list = apiService.showApiListByProject(projectId);
        Result result = new Result("1", list, "查询接口成功");
        return result;
    }

    @GetMapping("/showApiUnderApiClassification")
    public Result showApiUnderApiClassification(Integer apiClassificationId) {
        List<ApiListVO> list = apiService.showApiListByApiClassification(apiClassificationId);
        Result result = new Result("1", list, "查询接口成功");
        return result;
    }

    @PostMapping("/add")
    public Result addApi(Integer apiClassificationId,String apiName,String apiRequestMethod,String apiRequestUrl) {
        Api api = new Api();
        api.setApiClassificationId(apiClassificationId);
        api.setName(apiName);
        api.setMethod(apiRequestMethod);
        api.setUrl(apiRequestUrl);
        User user =(User) SecurityUtils.getSubject().getPrincipal();
        api.setCreateUser(user.getId());
        api.setCreateTime(new Date());
        apiService.save(api);
        Result result = new Result("1","接口添加成功");
        return result;
    }

    @GetMapping("/toApiView")
    public Result showApiViewVO(Integer apiId) {
        ApiVO list = apiService.findApiViewVO(apiId);
        Result result = new Result("1",list);
        return result;
    }


//    @PutMapping("/edit")
//    public Result toApiEdit(ApiVO apiEdit) {
//        // 直接根据apiid主键操作
//        apiService.updateById(apiEdit);
//        // delete原来 或者 先查下库，有结果就是更新 ，没有结果就是增加
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("api_id",apiEdit.get_id);
//        apiRequestParamService.remove(queryWrapper);
//    }

//    @RequestMapping("/run")
//    public Result run(ApiVO apiRunVO){
//
//
//    }

}
