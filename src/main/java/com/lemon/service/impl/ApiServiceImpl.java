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
        //远程调用
        RestTemplate restTemplate = new RestTemplate();
        String url = apiRunVO.getHost() + apiRunVO.getUrl();
        String method = apiRunVO.getMethod();
        List<ApiRequestParam> list = apiRunVO.getRequestParams();
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        LinkedMultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<String, String>();
        for (ApiRequestParam apiRequestParam : list) {
            if (apiRequestParam.getType() == 3) {
                // 头
                headers.add(apiRequestParam.getName(), apiRequestParam.getValue());
            } else if(apiRequestParam.getType()==4){
                String bodyParamsStr= apiRequestParam.getValue();
            } else{
                // body 2 type==1情况未处理
                bodyParams.add(apiRequestParam.getName(), apiRequestParam.getValue());
            }
        }

        HttpEntity httpEntity = new HttpEntity(bodyParams, headers);
        ResponseEntity responseEntity = null;
        ApiRunResult apiRunResult = new ApiRunResult();
        try {
            if ("get".equalsIgnoreCase(method)) {
                httpEntity = new HttpEntity(headers);
                responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            }
            if ("post".equalsIgnoreCase(method)) {
                httpEntity = new HttpEntity(bodyParams, headers);
                responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            }
        } catch (HttpStatusCodeException e) {
            // 注意此时有调用异常，往往没有body
            apiRunResult.setStatusCode(e.getStatusCode() + "");
            apiRunResult.setHeaders(JSON.toJSONString(e.getResponseHeaders()));
            apiRunResult.setBody(e.getResponseBodyAsString());
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
