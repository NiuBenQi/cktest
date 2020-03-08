package com.lemon.common;

import lombok.Data;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-07 09:42
 **/
@Data
public class ApiRunResult {

    private String statusCode;
    //httpheaders 是multivalueMap 需要转string
    private String headers;
    private String body;
}
