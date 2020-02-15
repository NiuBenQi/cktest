package com.lemon.service.impl;

import com.lemon.pojo.Api;
import com.lemon.mapper.ApiMapper;
import com.lemon.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

}
