package com.lemon.controller;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * @author benqi
 * @date 2020/5/8 20:31
 * @Description 合并文件 文件流操作
 */
public class readFile {

    public static void main(String[] args) {

        readFile readFile2 = new readFile();
        System.out.println(readFile2.fileCom());

        File file = new File("D:\\JAVA/test3.txt");
        readFile2.readFile(file);

    }

    public String readF(String dir){
        String str = "";
        try {
            File file = new File(dir);
            // 判断文件是否存在
            if (file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(  new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine())!=null){
                    str = str+lineTxt+"\r\n";
                }
                read.close();
            }else {
                System.out.println("找不到指定文件");
            }
        }catch (Exception e){
            System.out.println("读取文件内容错误");
            e.printStackTrace();
        }
        return str;
    }

    /**
     *  合并文件
     * @return
     */
    public String fileCom(){
        String str1 = readF("D://JAVA/test1.txt");
        String str2 = readF("D://JAVA/test2.txt");
        String str = str1 + str2;
        try {
            // 判断test3文件是否存在，如果存在先删除再写入
            File file = new File("D:\\JAVA/test3.txt");
            if (file.isFile() && file.exists()){
                file.delete();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\JAVA/test3.txt")));
            writer.write(str);
            writer.close();
        }catch (Exception e){
            System.out.println("写入文件错误");
        }
        return str;

    }

    public void readFile(File file){
        // 1.首先读取每一行
        try {
            InputStreamReader read = new InputStreamReader(  new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String readLine = null;

            while ((readLine = bufferedReader.readLine())!=null){
                int flag = readLine.indexOf("request");
                if(flag != -1){
                    JSON json = JSON.parseObject(readLine);
                    System.out.println(json);
                }
            }


        }catch (Exception e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }


        // 2.判断这一行中有没有包括接口名
        // 3.获取这一行文本，转格式，获取开始时间、结束时间 存到list集合中
        //
    }

















}
