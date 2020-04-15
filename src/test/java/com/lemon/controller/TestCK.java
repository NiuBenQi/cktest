package com.lemon.controller;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.alibaba.fastjson.JSONPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * @program: cktest
 * @description 支持junit+mockmvc
 * @author: NiuBenQi
 * @create: 2020-03-26 20:22
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestCK {
    @Autowired
    private MockMvc mockMvc;
    String sessionId;

    // 用户登录
    @Before
    @Test
    public void login() throws Exception {
        String resultJson = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username", "test@qq.com")
                .param("password", "96e79218965eb72c92a549dd5a330112"))
                .andDo(MockMvcResultHandlers.print())  //添加一个结果处理器
                .andExpect(MockMvcResultMatchers.status().isOk())   // 添加执行完成后的断言  状态码
                .andReturn().getResponse().getContentAsString();    // 执行完成后返回的相应信息
        sessionId = (String) JSONPath.read(resultJson, "$.massage");  // 获取相应信息中的sessionId
//        System.out.println("======================="+sessionId);
    }

    // 用户验重
    @Test
    public void Testfind() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("user/find")
                .header("Authorization", sessionId)
                .param("username", "test@qq.com")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    // 参数为json 新增project
    @Test
    public void projectAdd() throws Exception {
        String resultJson = mockMvc.perform(MockMvcRequestBuilders.post("/project/add2")
                .header("Authorization", sessionId)
                .content("{\"name\":\"ck\",\"host\":\"http:admin.ck.org\"}"))
//                .andDo()
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();


    }
}
