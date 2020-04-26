package com.lemon.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @program: cktest
 * @description 读取自定义yml文件
 * @author: NiuBenQi
 * @create: 2020-03-14 09:10
 **/
@Component
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "logging.level")
@Data
public class YamlProperties {
    @Value("${level}")
    private String root;
    @Value("${com.lemon.mapper}")
    private String mapper;
}
