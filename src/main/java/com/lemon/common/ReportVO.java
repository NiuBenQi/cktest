package com.lemon.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lemon.pojo.TestReport;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: cktest
 * @description 测试报告
 * @author: NiuBenQi
 * @create: 2020-04-27 11:36
 **/
@Data
public class ReportVO {
    private Integer id;
    /**
     * 套件名称
     */
    private String name;
    /**
     * 测试人
     */
    private String username;
    /**
     * 生成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createReprotTime;
    /**
     * 总用例数 计算通过率
     */
    private int totalCase;
    /**
     * 成功通过数
     */
    private int successes;

    /**
     * 失败数
     */
    private int failures;
    /**
     * case 列表
     */
    private List<CaseListVO> caseList;

    public int totalCase() {
        return caseList.size();
    }

    public int successes() {
        int count1 = 0, count2 = 0;
        for (CaseListVO caseListVO : caseList) {
            TestReport testReport = caseListVO.getTestReport();
            if (testReport != null) {
                if (testReport.getPassFlag().equals("通过")) {
                    count1++;
                } else {
                    count2++;
                }
            }
        }
        this.successes = count1;
        this.failures = count2;
        return successes;
    }

    public int getFailures() {
        return failures;
    }

}
