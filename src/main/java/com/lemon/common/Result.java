package com.lemon.common;

import lombok.Data;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-02-14 22:17
 **/
@Data
public class Result {
    public String status;
    public Object data;
    public String massage;

    public Result(String status, Object data, String massage) {
        this.status = status;
        this.data = data;
        this.massage = massage;
    }

    public Result(String status, String massage) {
        this.status = status;
        this.massage = massage;
    }

}
