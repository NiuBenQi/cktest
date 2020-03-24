package com.lemon.mapper;

import com.lemon.common.CaseEditVO;
import com.lemon.common.CaseListVO;
import com.lemon.pojo.Cases;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
public interface CasesMapper extends BaseMapper<Cases> {
    @Select("SELECT * from cases WHERE suite_id=#{suitId}")
    List<Cases> findAll(Integer suitId);

    @Select("SELECT DISTINCT t1.*, t6.id apiId, t6.url apiUrl FROM cases t1 LEFT JOIN suite t2 ON t1.suite_id = t2.id LEFT JOIN project t3 ON t2.project_id = t3.id LEFT JOIN case_param_value t4 ON t1.id = t4.case_id LEFT JOIN api_request_param t5 ON t4.api_request_param_id = t5.id LEFT JOIN api t6 ON t5.api_id = t6.id WHERE t3.id = #{projectId}")
    List<CaseListVO> showCaseUnderProject(Integer projectId);

    @Select("SELECT DISTINCT t1.*, t6.id apiId, t6.url apiUrl FROM cases t1 LEFT JOIN suite t2 ON t1.suite_id = t2.id LEFT JOIN case_param_value t4 ON t1.id = t4.case_id LEFT JOIN api_request_param t5 ON t4.api_request_param_id = t5.id LEFT JOIN api t6 ON t5.api_id = t6.id WHERE t1.suite_id = #{suiteId}")
    List<CaseListVO> showCaseUnderSuite(String suiteId);

    @Select("SELECT DISTINCT t1.*, t4.id apiId, t4.url, t4.method, t6.`host` FROM cases t1 LEFT JOIN case_param_value t2 ON t2.case_id = t1.id LEFT JOIN api_request_param t3 ON t2.api_request_param_id = t3.id LEFT JOIN api t4 ON t3.api_id = t4.id LEFT JOIN api_classification t5 ON t4.api_classification_id = t5.id LEFT JOIN project t6 ON t5.project_id = t6.id WHERE t1.id = #{caseId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "requestParams",column = "id",many = @Many(select="com.lemon.mapper.ApiRequestParamMapper.findbyCase")),
            @Result(property = "testRules",column = "id",many = @Many(select="com.lemon.mapper.TestRuleMapper.findByCase"))
    })
    CaseEditVO findCaseEditVo(String caseId);
}
