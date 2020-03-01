package com.lemon.common;


import com.lemon.pojo.Api;
import com.lemon.pojo.ApiClassification;
import lombok.Data;


import java.util.List;

/**
 * @program: cktest
 * @description 封装分类信息和接口信息
 * @author: NiuBenQi
 * @create: 2020-02-25 20:30
 **/
@Data
public class ApiClassificationVO extends ApiClassification {

    // 关联对象
    List<Api> apis;

}
