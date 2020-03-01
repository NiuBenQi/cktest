package com.lemon.mapper;

import com.lemon.pojo.Api;
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
public interface ApiMapper extends BaseMapper<Api> {

    @Select("SELECT * FROM api WHERE api_classification_id=#{apiClassfication}")
    public List<Api> findApi(Integer apiClassfication);
}
