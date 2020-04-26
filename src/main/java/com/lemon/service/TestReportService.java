package com.lemon.service;

import com.lemon.pojo.TestReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.pojo.TestRule;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface TestReportService extends IService<TestReport> {

    List<TestReport> run(Integer suiteId);
}
