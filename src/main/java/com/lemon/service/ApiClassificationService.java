package com.lemon.service;

import com.lemon.common.ApiClassificationVO;
import com.lemon.pojo.ApiClassification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface ApiClassificationService extends IService<ApiClassification> {

    public List<ApiClassificationVO> getWithApi(Integer projectId);
}
