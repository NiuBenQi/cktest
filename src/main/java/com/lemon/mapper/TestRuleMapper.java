package com.lemon.mapper;

import com.lemon.common.CaseEditVO;
import com.lemon.pojo.TestRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface TestRuleMapper extends BaseMapper<TestRule> {
    /**
     * 根据suiteId查询 所有的case信息
     * @param suiteId
     * @return
     */
    @Select("SELECT DISTINCT t1.*, t6.id apiId, t6.url,t6.host FROM cases t1 LEFT JOIN suite t2 ON t1.suite_id = t2.id LEFT JOIN case_param_value t4 ON t1.id = t4.case_id LEFT JOIN api_request_param t5 ON t4.api_request_param_id = t5.id LEFT JOIN api t6 ON t5.api_id = t6.id WHERE t1.suite_id = #{suiteId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "requestParams",column = "id",many = @Many(select="com.lemon.mapper.ApiRequestParamMapper.findbyCase")),
            @Result(property = "testRules",column = "id",many = @Many(select="com.lemon.mapper.TestRuleMapper.findByCase"))
    })
    List<CaseEditVO> findCaseEditVosUnderSuite(Integer suiteId);

    @Delete("DELETE FROM test_report WHERE case_id IN ( SELECT id FROM cases WHERE suite_id = #{suiteId} )")
    void deleteReport(Integer suiteId);
}
