package com.lemon.mapper;

import com.lemon.pojo.Cases;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface CasesMapper extends BaseMapper<Cases> {
    @Select("SELECT * from cases WHERE suite_id=#{suitId}")
    List<Cases> findAll(Integer suitId);

//    SELECT t1.*,t6.* from cases t1
//    LEFT JOIN suite t2 on t1.suite_id = t2.id
//    LEFT JOIN project t3 on t2.project_id = t3.id
//    LEFT JOIN case_param_value t4 on t1.id = t4.case_id
//    LEFT JOIN api_request_param t5 on t4.api_request_param_id=t5.id
//    LEFT JOIN api t6 on t5.api_id=t6.id
//    WHERE t3.id=1;

}
