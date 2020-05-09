package com.lemon.controller;

import com.lemon.util.YamlProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.PriorityQueue;

/**
 * @program: cktest
 * @description  读取自定义yml文件
 * @author: NiuBenQi
 * @create: 2020-04-21 10:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestYamlLoader {

    @Autowired
    private YamlProperties yamlProperties;

    @Test
    public void  test(){
        System.out.println(yamlProperties.toString());
    }
}
