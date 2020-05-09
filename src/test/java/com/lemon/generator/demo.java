package com.lemon.generator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-03-24 22:17
 **/
public class demo {
    public static void main(String[] args) {
        demo d = new demo();
        int[] arr = new int[]{1, 4, 2, 3, 5, 8};
//        MaoPao(arr);
//        System.out.println(twoSearch(arr, 2));
//        System.out.println(find30(3));
//        System.out.println(revert(arr,1,5));
//        System.out.println(two(arr, 8));
          test001();
    }


    public String reverse(String str) {
        String[] strs = str.split(" ");
        List<String> list = new ArrayList<>();
        // 将数组中的每个字符串，倒序插叙list列表中
        for (String str1 : strs) {
            StringBuffer sb = new StringBuffer(str1);
            StringBuffer res = sb.reverse();
            String printOut = res.toString() + " ";
            list.add(printOut);
        }
        // 将得到的list集合倒序输出
        for (int i = 0; i < list.size() / 2; i++) {
            String str1 = list.get(list.size() - 1 - i);
            str1 = list.set(i, str1);
            list.set(list.size() - 1 - i, str1);
        }
        return list.toString();
    }

    /**
     * 算法1：冒泡排序
     * 2: 二分查找
     * 3：插叙
     */
    public void maoPao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int a : arr) {
            System.out.print(a + " ");
        }

    }

    /**
     * @param arr   要查询的数组
     * @param value 查询的数字
     */
    public static int twoSearch(int[] arr, int value) {
        //1.首先将数组变为有序
        Arrays.sort(arr);
        System.out.print("排好序的数组为：");
        for (int array : arr) {
            System.out.print(array + " ");
        }
        System.out.println("要找的数是：" + value);
        // 设置最小数、最大数
        int low = 0;
        int hight = arr.length - 1;
        System.out.println("hight:" + hight);
        //2.while循环 value 是否大于中位数
        while (low <= hight) {
            int mid = (low + hight) / 2;
            if (value == arr[mid]) {
                return mid;
            }
            if (value > arr[mid]) {
                low = mid + 1;
            }
            if (value < arr[mid]) {
                hight = mid - 1;
            }
        }

        //未找到返回 -1
        return -1;
    }

    /**
     * 一列数的规则如下: 1、1、2、3、5、8、13、21、34...... 求第30位数是多少， 用递归算法实现。
     */
    public static int find30(int n) {
        if (n <= 0) {
            return 0;

        } else if (n > 0 && n <= 2) {
            return 1;
        }
        return find30(n - 1) + find30(n - 2);
    }

    /**
     * 将一整数逆序后放入一数组中（要求递归实现） Ex : 1234 变为 {4,3,2,1}
     */
    public static int revert(int rs[], int i, int number) {
        if (i < rs.length) {
            rs[i] = number % 10;
            number = (number - number % 10) / 10;
            return revert(rs, i + 1, number);
        } else {
            return 0;
        }
    }

    public static void MaoPao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int array : arr) {
            System.out.print(array + " ");
        }
    }

    public static int two(int[] arr, int value) {
        Arrays.sort(arr);
        int min = 0;
        int max = arr.length - 1;
        System.out.println("min:" + min + "  max:" + max);

        while (min <= max) {
            int mind = (min + max) / 2;
            if (value == arr[mind]) {
                return mind;
            }
            if (value > arr[mind]) {
                min = arr[mind];
            } else if (value < arr[mind]) {
                max = arr[mind];
            }
        }
        return -1;
    }


    public static void test001() {
        // 创建可缓存的线程池，可重复利用
        ExecutorService newExecutorService = Executors.newCachedThreadPool();
        // 创建10个线程
        for (int i = 0; i < 10; i++) {
            int temp = i;
            newExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName:"+Thread.currentThread().getName()+",i"+temp);
                }
            });
        }
    }
}
