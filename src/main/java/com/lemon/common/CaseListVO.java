package com.lemon.common;

import com.lemon.pojo.TestReport;
import lombok.Data;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-19 20:18
 **/
@Data
public class CaseListVO {

    private String id;
    private String name;
    private String apiId;
    private String apiUrl;

    private TestReport testReport;

}
