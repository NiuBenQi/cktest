package com.lemon.util;

import com.mysql.jdbc.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @program: cktest
 * @description 判断对象是否为空
 * @author: NiuBenQi
 * @create: 2020-03-16 21:36
 **/
public class IsEmpty {
    public static boolean isEmpty(Object obj){
        if(obj==null){
            return true;
        }

        if(obj instanceof String){
            return StringUtils.isEmptyOrWhitespaceOnly((String)obj);
        }

        if(obj instanceof Collection &&((Collection<?>)obj).isEmpty()){
            return true;
        }

        if(obj.getClass().isArray()&&Array.getLength(obj)==0){
            return true;
        }

        if(obj instanceof Map &&((Map<?, ?>)obj).isEmpty()){
            return true;
        }
        return false;
    }

}
