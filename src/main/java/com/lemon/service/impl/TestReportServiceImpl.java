package com.lemon.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.lemon.common.ApiRunResult;
import com.lemon.common.CaseEditVO;
import com.lemon.common.ReportVO;
import com.lemon.mapper.TestRuleMapper;
import com.lemon.pojo.ApiRequestParam;
import com.lemon.pojo.TestReport;
import com.lemon.mapper.TestReportMapper;
import com.lemon.pojo.TestRule;
import com.lemon.pojo.User;
import com.lemon.service.TestReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@Service
public class TestReportServiceImpl extends ServiceImpl<TestReportMapper, TestReport> implements TestReportService {

    @Autowired
    TestRuleMapper testRuleMapper;

    @Autowired
    TestReportMapper testReportMapper;
    /**
     * 循环运行api run 返回一个testReport集合
     *
     * @param suiteId
     * @return
     */
    @Override
    public List<TestReport> run(Integer suiteId) {
        // 1、先根据suiteId查询
        List<CaseEditVO> list = testRuleMapper.findCaseEditVosUnderSuite(suiteId);
        List<TestReport> reportList = new ArrayList<>();
        for (CaseEditVO caseEditVO : list) {
            //2.远程调用api执行
            TestReport testReport = runAndGetReport(caseEditVO);
            reportList.add(testReport);
        }
        // 3.对test_report表 先删除再插入
        testRuleMapper.deleteReport(suiteId);
        this.saveBatch(reportList);
        return reportList;

    }

    @Override
    public TestReport findByCaseId(Integer caseId) {
        return testReportMapper.findByCaseId(caseId);
    }

    @Override
    public ReportVO getReport(Integer suiteId) {
        ReportVO report = testReportMapper.getReport(suiteId);
        User user= (User)SecurityUtils.getSubject().getPrincipal();
        report.setUsername(user.getUsername());
        report.setCreateReprotTime(new Date());
        return report;
    }

    /**
     * 程调用api执行
     *
     * @param caseEditVO
     * @return
     */
    TestReport runAndGetReport(CaseEditVO caseEditVO) {
        TestReport testReport = new TestReport();
        //远程调用  restTempplate HTTP发送get、post、put、delete请求
        RestTemplate restTemplate = new RestTemplate();
        String url = caseEditVO.getHost() + caseEditVO.getUrl();
        String method = caseEditVO.getMethod();
        List<ApiRequestParam> list = caseEditVO.getRequestParams();
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        LinkedMultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<String, String>();
        String paramStr = "?";
        String bodyParamsStr = null;
        for (ApiRequestParam apiRequestParam : list) {
            // 将不同的请求信息循环放入对应的类型中 1. param --2.body  --3.head --4.json格式的请求体
            if (apiRequestParam.getType() == 1) {
                paramStr += apiRequestParam.getName() + "=" + apiRequestParam.getValue() + "&";
            }
            if (apiRequestParam.getType() == 3) {
                // 头
                headers.add(apiRequestParam.getName(), apiRequestParam.getValue());
            } else if (apiRequestParam.getType() == 4) {
                bodyParamsStr = apiRequestParam.getValue();
            } else if (apiRequestParam.getType() == 2) {
                // body 2
                bodyParams.add(apiRequestParam.getName(), apiRequestParam.getValue());
            }
        }
        // paramtype=1时，去掉url最后一个&
        if (!"?".equals(paramStr)) {
            paramStr = paramStr.substring(0, paramStr.lastIndexOf("&"));
        }
        HttpEntity httpEntity = new HttpEntity(bodyParams, headers);
        // 给responseEntity 赋个默认值 POST 请求
        ResponseEntity responseEntity = null;
        ApiRunResult apiRunResult = new ApiRunResult();
        try {
            if ("get".equalsIgnoreCase(method)) {
                httpEntity = new HttpEntity(headers);
                responseEntity = restTemplate.exchange(url + paramStr, HttpMethod.GET, httpEntity, String.class);
            }
            if ("post".equalsIgnoreCase(method)) {
                if (bodyParamsStr != null && bodyParamsStr != "") {
                    // json 格式的post请求  4 类型
                    httpEntity = new HttpEntity(bodyParamsStr, headers);
                    testReport.setRequestBody(JSON.toJSONString(bodyParams));
                    responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
                } else {
                    // 执行请求并返回结果  2类型
                    httpEntity = new HttpEntity(bodyParams, headers);
                    testReport.setRequestBody(JSON.toJSONString(bodyParams));
                    responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
                }
            }
            if ("put".equalsIgnoreCase(method)) {
                httpEntity = new HttpEntity(bodyParams, headers);
                // 执行请求并返回结果
                responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
            }
            if ("delete".equalsIgnoreCase(method)) {
                httpEntity = new HttpEntity(bodyParams, headers);
                // 执行请求并返回结果
                responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
            }

            // 参数注入
            testReport.setCaseId(caseEditVO.getId());
            testReport.setRequestUrl(url);
            testReport.setRequestHeaders(JSON.toJSONString(headers));
            testReport.setRequestHeaders(JSON.toJSONString(responseEntity.getHeaders()));
            testReport.setResponseBody(responseEntity.getBody().toString());
            testReport.setPassFlag(assertByTestRule(responseEntity.getBody().toString(), caseEditVO.getTestRules()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testReport;
    }

    String assertByTestRule(String responseBode, List<TestRule> testRules) {
        boolean flag = true;
        for (TestRule testRule : testRules) {
            Object value = JSONPath.read(responseBode, testRule.getExpression());
            String actual = (String) value;
            String op = testRule.getOperator();
            if (op.equals("=")) {
                if (!value.equals(testRule.getExpected())) {
                    flag = false;
                }
            } else {
                if (!actual.contains(testRule.getExpected())) {
                    flag = false;
                }
            }
        }
        if (!flag) {
            return "不通过";
        }

        return "通过";
    }
}
