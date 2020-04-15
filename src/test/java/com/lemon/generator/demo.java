package com.lemon.generator;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-24 22:17
 **/
public class demo {
    public static void main(String[] args) {
        String str = "hello world ni hao";
        String[] strs = str.split(" ");
        String text = strs[strs.length - 1];
        System.out.println( text.length());
    }
}
