package com.lemon.mapper;

import com.lemon.common.CaseListVO;
import com.lemon.pojo.TestReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}
