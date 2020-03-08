package com.lemon.service.impl;

import com.lemon.common.ApiListVO;
import com.lemon.common.ApiVO;
import com.lemon.pojo.Api;
import com.lemon.mapper.ApiMapper;
import com.lemon.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {
    @Autowired
    ApiMapper apiMapper;

    @Override
    public List<ApiListVO> showApiListByProject(Integer projectId) {
        return apiMapper.showApiListByProject(projectId);
    }

    @Override
    public List<ApiListVO> showApiListByApiClassification(Integer apiClassfication) {
        return apiMapper.showApiListByApiClassification(apiClassfication);
    }

    @Override
    public ApiVO findApiViewVO(Integer apiId) {
        return apiMapper.findApiViewVO(apiId);
    }
}
