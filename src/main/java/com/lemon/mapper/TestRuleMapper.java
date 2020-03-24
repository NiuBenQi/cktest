package com.lemon.mapper;

import com.lemon.pojo.TestRule;
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
public interface TestRuleMapper extends BaseMapper<TestRule> {
    @Select("SELECT * from test_rule where case_id=#{caseId}")
    public List<TestRule> findByCase(String caseId);
}
