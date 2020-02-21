package com.lemon.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: cktest
 * @description 自动填充
 * @author: NiuBenQi
 * @create: 2020-02-18 20:25
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间自动填充为当前时间 / 每个表同一个字段名也OK
        this.setFieldValByName("regtime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
