package com.lemon.mapper;

import com.lemon.common.ApiListVO;
import com.lemon.common.ApiVO;
import com.lemon.pojo.Api;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.pojo.Project;
import org.apache.ibatis.annotations.*;


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

    @Select("SELECT t1.* ,t2.`name` classificationName from api t1,api_classification t2 where t2.project_id=#{projectId} and t1.api_classification_id = t2.id;")
    public List<ApiListVO> showApiListByProject(Integer projectId);

    @Select("SELECT t1.* ,t2.`name` classificationName from api t1,api_classification t2 where t2.id=#{apiClassfication} and t1.api_classification_id = t2.id")
    public List<ApiListVO> showApiListByApiClassification(Integer apiClassfication);


    /** 利用延迟加载 先两表查询出接口信息，然后调用findAll 一对多的方式 **/
    @Select("SELECT t1.*,t2.username createUsername from api t1,user t2 WHERE  t1.id=#{apiId} and t1.create_user=t2.id;")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "requestParams", column = "id", many = @Many(select = "com.lemon.mapper.ApiRequestParamMapper.findAll"))
    })

    public ApiVO findApiViewVO(Integer apiId);
}
