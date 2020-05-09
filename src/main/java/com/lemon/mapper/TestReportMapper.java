package com.lemon.mapper;

import com.lemon.common.CaseListVO;
import com.lemon.common.ReportVO;
import com.lemon.pojo.TestReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
public interface TestReportMapper extends BaseMapper<TestReport> {

    List<CaseListVO> findCaseEditVoUnderSuite(Integer suiteId);

    @Select("select * from test_report where case_Id = #{caseId}")
    TestReport findByCaseId(Integer caseId);

    @Select("select * from suite where id = #{suiteId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "caseList",column = "id",many = @Many(select="com.lemon.mapper.CasesMapper.showCaseUnderSuite"))
    })
    ReportVO getReport(Integer suiteId);
}
