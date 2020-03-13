package com.lemon.service;

import com.lemon.common.ApiListVO;
import com.lemon.common.ApiRunResult;
import com.lemon.common.ApiVO;
import com.lemon.common.Result;
import com.lemon.pojo.Api;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface ApiService extends IService<Api> {

    public List<ApiListVO> showApiListByProject(Integer projectId);

    public List<ApiListVO> showApiListByApiClassification(Integer apiClassfication);

    public ApiVO findApiViewVO(Integer apiId);

    public ApiRunResult run(ApiVO apiRunVO);
}
