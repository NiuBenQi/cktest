package com.lemon.common;

import com.lemon.pojo.Api;
import com.lemon.pojo.ApiRequestParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-03 21:48
 **/
@Data
public class ApiVO  extends Api {

    private String createUsername;
    private String host;

    private List<ApiRequestParam> requestParams = new ArrayList<ApiRequestParam>();
    private List<ApiRequestParam> queryParams = new ArrayList<ApiRequestParam>();
    private List<ApiRequestParam> bodyParams = new ArrayList<ApiRequestParam>();
    private List<ApiRequestParam> headerParams = new ArrayList<ApiRequestParam>();
    private List<ApiRequestParam> bodyRawParams = new ArrayList<ApiRequestParam>();

}
