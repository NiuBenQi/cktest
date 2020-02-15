package com.lemon;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: cktest
 * @description 启动类
 * @author: NiuBenQi
 * @create: 2020-02-11 21:32
 **/

@MapperScan(basePackages = "com.lemon.mapper")
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);

    }
}
