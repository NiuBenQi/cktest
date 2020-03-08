package com.lemon.common;

import lombok.Data;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-01 21:49
 **/
@Data
public class ApiListVO {

    private String id;
    private String name;
    private String method;
    private String url;
    private String classificationName;


}
