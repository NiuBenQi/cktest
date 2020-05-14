package com.lemon.service.impl;

import com.alibaba.fastjson.JSON;

import com.lemon.common.ApiListVO;
import com.lemon.common.ApiRunResult;
import com.lemon.common.ApiVO;
import com.lemon.pojo.Api;
import com.lemon.mapper.ApiMapper;
import com.lemon.pojo.ApiRequestParam;
import com.lemon.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.util.IsEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@Slf4j
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {
    @Autowired
    ApiMapper apiMapper;

    @Override
    public List<ApiListVO> showApiListByProject(Integer projectId) {
        return apiMapper.showApiListByProject(projectId);
    }

    @Override
    public List<ApiListVO> showApiListByApiClassification(Integer apiClassfication) {
        return apiMapper.showApiListByApiClassification(apiClassfication);
    }

    @Override
    public ApiVO findApiViewVO(Integer apiId) {
        return apiMapper.findApiViewVO(apiId);
    }

    @Override
    public ApiRunResult run(ApiVO apiRunVO) {
        //远程调用  restTempplate HTTP发送get、post、put、delete请求
        RestTemplate restTemplate = new RestTemplate();
        String url = apiRunVO.getHost() + apiRunVO.getUrl();
        String method = apiRunVO.getMethod();
        List<ApiRequestParam> list = apiRunVO.getRequestParams();
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        LinkedMultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<String, String>();
        String paramStr = "?";
        String bodyParamsStr = null;
        ApiRunResult apiRunResult = new ApiRunResult();
        try {
            for (ApiRequestParam apiRequestParam : list) {
                // 将不同的请求信息循环放入对应的类型中 1. param --2.body  --3.head --4.json格式的请求体
                if(apiRequestParam.getType() == 1){
                    paramStr += apiRequestParam.getName()+"="+apiRequestParam.getValue()+"&";
                }
                if (apiRequestParam.getType() == 3) {
                    // 头 3
                    headers.add(apiRequestParam.getName(), apiRequestParam.getValue());
                } else if(apiRequestParam.getType()==4){
                    bodyParamsStr = apiRequestParam.getValue();
                } else if (apiRequestParam.getType()==2){
                    // body 2
                    bodyParams.add(apiRequestParam.getName(), apiRequestParam.getValue());
                }
            }
        }catch (Exception e){
            apiRunResult.setStatusCode("999");
            apiRunResult.setHeaders("");
            apiRunResult.setBody("请求参数不正确");
            log.info("apiRequestParam中的参数异常{}",e);
            return apiRunResult;
        }

        // paramtype=1时，去掉url最后一个&
        if(!"?".equals(paramStr)){
            paramStr = paramStr.substring(0,paramStr.lastIndexOf("&"));
        }
        System.out.println("================"+paramStr);
        // httpEntity 代表底层流的基本实体。通常是在http报文中获取的实体
        HttpEntity httpEntity = new HttpEntity(bodyParams, headers);
        // 给responseEntity 赋个默认值 POST 请求
        ResponseEntity responseEntity = null;

        try {
            if ("get".equalsIgnoreCase(method)) {
                httpEntity = new HttpEntity(headers);
                responseEntity = restTemplate.exchange(url+paramStr, HttpMethod.GET, httpEntity, String.class);
            }
            if ("post".equalsIgnoreCase(method)) {
                if (bodyParamsStr !=null && bodyParamsStr != ""){
                    // json 格式的post请求  4 类型
                    httpEntity = new HttpEntity(bodyParamsStr, headers);
                    responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
                }else{
                    // 执行请求并返回结果  2类型
                    httpEntity = new HttpEntity(bodyParams, headers);
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
        } catch (HttpStatusCodeException e) {
            // 注意此时有调用异常，往往没有body
            apiRunResult.setStatusCode(e.getStatusCode() + "");
            apiRunResult.setHeaders(JSON.toJSONString(e.getResponseHeaders()));
            apiRunResult.setBody(e.getResponseBodyAsString());
            return apiRunResult;
        }
        apiRunResult.setStatusCode(responseEntity.getStatusCode() + "");
        HttpHeaders headersResult = responseEntity.getHeaders();
        // spring 框架自带的java——》string
//        apiRunResult.setHeaders(new ObjectMapper().writeValueAsString(headersResult));
        apiRunResult.setHeaders(JSON.toJSONString(headersResult));
        apiRunResult.setBody(responseEntity.getBody().toString());
        return apiRunResult;
    }
}
