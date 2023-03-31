package com.study.design.decorate.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LowerCaseInputStream extends FileInputStream {
    public LowerCaseInputStream(File file) throws FileNotFoundException {
        super(file);
    }


    /**
     * 读取一个个字符
     * @return
     * @throws IOException
     */
    @Override
    public  int read() throws IOException{
        int c = super.read();
        return ( c == -1 ? c : Character.toLowerCase((char)c));
    }


    /**
     * 读取字节数组
     * @param b  字节数组
     * @param offset  开始读取的位置
     * @param len  读取字节的长度
     * @return  读取的字符个数
     * @throws IOException
     */
    @Override
    public int read(byte[] b, int offset, int len) throws IOException {

        // 获取读取的字符个数
        int result = super.read(b,offset,len);
        for(int i = offset; i< offset + result; i++){
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return result;

    }
}
