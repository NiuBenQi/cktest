package com.lemon.nio;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @program: cktest
 * @description
 * @author: NiuBenQi
 * @create: 2020-05-09 10:18
 **/
public class NewIoChannel {

    private String file = "";
    private String file2 = "";

    @Before
    public void init() {
        file = NewIoChannel.class.getResource("").getPath() + "\\myfile.txt";
        file2 = NewIoChannel.class.getResource("").getPath() + "\\myfile2.txt";
        System.out.println(file);
    }


    @Test
    public void FileTest() throws IOException {
        String info[] = {"wang", "fwefwe", "北京"};
        File file = new File(this.file);

        FileOutputStream output = null;
        output = new FileOutputStream(file);
        // 设置通道
        FileChannel fout = null;
        fout = output.getChannel();
        // 设置nio缓存区大小 1024
        ByteBuffer buf = ByteBuffer.allocate(1024);
        for (int i = 0; i < info.length; i++) {
            // 将数组内容添加到缓存区
            buf.put(info[i].getBytes("UTF-8"));
        }
        // 将buffer从写模式切换到读模式
        buf.flip();
        // 将缓存区的内容写到通道中
        fout.write(buf);
        // 关闭通道、缓存区
        fout.close();
        output.close();
    }

    @Test
    public void writeFile() throws IOException {
        File file = new File(this.file);
        // 字节输入流
        FileInputStream input = new FileInputStream(file);
        FileChannel fileChannel = input.getChannel();
        byte data[] = new byte[(int) file.length()];
        //      MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        //      int foot = 0;
        //      while(mbb.hasRemaining())
        //      data[foot++] = mbb.get();
        ByteBuffer bufs = ByteBuffer.wrap(data);
        fileChannel.read(bufs);
        System.out.println(new String(data));
        fileChannel.close();
    }

    @Test
    public void writereadFile() throws IOException{
        File file = new File(this.file);
        File file2 = new File(this.file2);
        FileInputStream input = new FileInputStream(file);
        FileOutputStream output = new FileOutputStream(file2);

        FileChannel fout = output.getChannel();
        FileChannel fin = input.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int temp =0;
        while ((temp = fin.read(buf)) != -1) {
            buf.flip();
            fout.write(buf);
            buf.clear();
        }
        fin.close();
        fout.close();
    }

    @Test
    public void FileLockDemo() throws IOException, InterruptedException{
        File file = new File(this.file);
        FileOutputStream output = new FileOutputStream(file,true);
        FileChannel fout = output.getChannel();
        FileLock lock = fout.tryLock();
        if(lock != null){
            System.out.println(file.getName()+"文件被锁定5秒");
            Thread.sleep(5000);
            lock.release();
            System.out.println(file.getName()+"文件被解锁");
        }
        fout.close();
        output.close();
    }

}
