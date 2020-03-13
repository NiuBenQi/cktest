package com.lemon.common;

import lombok.Data;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-12 08:09
 **/
@Data
public class ApiRunResult {

    private String statusCode;
    private String headers;
    private String body;
}
