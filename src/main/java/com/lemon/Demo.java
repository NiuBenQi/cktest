package com.lemon;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-02-13 15:01
 **/
public class Demo {

//    //    设置一个hashmap，key储存身份证信息，value储存申请时的时间信息
////    public static final Map<String, Long> MAP = new HashMap<String, Long>();
////    public static final long OUT_TIME = 1000;
////    public static final int OUT_SIZE = 100;
////
////    //验证是否短时间重复申请
////    public static boolean isRepeat(String idNum) {
////        boolean flag = false;
////        //检查是否存在key
//////        信息存入map之前进行判断，如果这个key在map中存在则判断其value与现在时间是否超过允许重复提交时长，如果是则更新数据，返回通过，否则返回不通过
////        if (MAP.containsKey(idNum)) {
////            if (System.currentTimeMillis() - MAP.get(idNum) > OUT_TIME) {
////                flag = true;
////            } else {
////                flag = false;
////            }
////        } else {
////            flag = true;
////        }
//////        在一笔申请提交进来的时候，将身份证信息和时间信息存入map
////        MAP.put(idNum, System.currentTimeMillis());
////        //当数量过多做一次清除
//////        在map达到一定大小时将开一个子线程，循环遍历map中的值，将与当前时间相差大于允许重复提交时间的键全部删除
////        if (MAP.size() > OUT_SIZE) {
////            new Thread() {
////                @Override
////                public void run() {
////                    for (Entry<String, Long> entry : MAP.entrySet()) {
////                        if (System.currentTimeMillis() - entry.getValue() > OUT_TIME) {
////                            MAP.remove(entry.getKey());
////                        }
////                    }
////                }
////            }.start();
////        }
////        return flag;
////    }
}
