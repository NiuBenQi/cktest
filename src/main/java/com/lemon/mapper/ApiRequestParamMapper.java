package com.lemon.mapper;

import com.lemon.pojo.ApiRequestParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface ApiRequestParamMapper extends BaseMapper<ApiRequestParam> {

    @Select("SELECT * from api_request_param WHERE api_id=#{apiId};")
    public List<ApiRequestParam> findAll(Integer apiId);
}
