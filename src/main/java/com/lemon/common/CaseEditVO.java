package com.lemon.common;

import com.lemon.pojo.ApiRequestParam;
import com.lemon.pojo.TestRule;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: cktest
 * @description 封装一个对象 用作展示caseEdit页面上的数据
 * @author: NiuBenQi
 * @create: 2020-03-22 11:31
 **/
@Data
public class CaseEditVO {
    private Integer id;
    private String name;
    private String method;
    private String url;
    private Integer apiId;
    private String host;
    private List<ApiRequestParam> requestParams = new ArrayList<ApiRequestParam>();
    private List<TestRule> testRules = new ArrayList<TestRule>();
}
