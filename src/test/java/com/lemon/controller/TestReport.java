package com.lemon.controller;

import com.alibaba.fastjson.JSONPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-04-29 18:10
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestReport {
    @Autowired
    private MockMvc mockMvc;
    String sessionId;

    // 用户登录
    @Before
    @Test
    public void login() throws Exception {
        String resultJson = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username", "test@qq.com")
                .param("password", "e10adc3949ba59abbe56e057f20f883e"))
                .andDo(MockMvcResultHandlers.print())  //添加一个结果处理器
                .andExpect(MockMvcResultMatchers.status().isOk())   // 添加执行完成后的断言  状态码
                .andReturn().getResponse().getContentAsString();    // 执行完成后返回的相应信息
        sessionId = (String) JSONPath.read(resultJson, "$.massage");  // 获取相应信息中的sessionId
//        System.out.println("======================="+sessionId);
    }

    @Test
    public void get() throws Exception {
        String resultJson = mockMvc.perform(MockMvcRequestBuilders.get("/testReport/get")
            .header("sessionId",sessionId)
            .param("suiteId","1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getResponse().getContentAsString();
    }

}
