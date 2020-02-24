package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.Result;
import com.lemon.pojo.Project;
import com.lemon.pojo.User;
import com.lemon.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/project")
//@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/toList")
    @ApiOperation(value = "根据userID获取projectlist", httpMethod = "GET")
    public Result toList(Integer userId) {
        Result result = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("create_user", userId);
        List list = projectService.list(queryWrapper);
        result = new Result("1", list, "成功");
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加项目", httpMethod = "POST")
    public Result add(Project project) {
        Result result = null;
        // 从subject中取出登录信息
        User user =(User) SecurityUtils.getSubject().getPrincipal();
        // 将拿到的用户id存到
        project.setCreateUser(user.getId());
        projectService.save(project);
        result = new Result("1", "项目添加成功");
        return result;
    }

    @GetMapping("/{projectId}")
    @ApiOperation(value = "根据projectId查询项目", httpMethod = "GET")
    public Result getById (@PathVariable("projectId") Integer projectId) {
        Result result = null;
        Project project = projectService.getById(projectId);
        result = new Result("1",project, "查询项目");
        return result;
    }

    @PostMapping("/{projectId}")
    @ApiOperation(value = "更新项目", httpMethod = "POST")
    public Result UpdateById (@PathVariable("projectId") Integer projectId,Project project) {
        Result result = null;
        project.setId(projectId);
        User user =(User) SecurityUtils.getSubject().getPrincipal();
        // 将拿到的用户id存到
        project.setCreateUser(user.getId());
        projectService.updateById(project);
        result = new Result("1",project, "更新项目成功");
        return result;
    }

    @DeleteMapping("/{projectId}")
    @ApiOperation(value = "根据projectId删除项目", httpMethod = "DELETE")
    public Result RemoveById (@PathVariable("projectId") Integer projectId) {
        Result result = null;
        projectService.removeById(projectId);
        result = new Result("1", "删除成功");
        return result;
    }
}
