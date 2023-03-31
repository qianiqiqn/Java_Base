package com.study.design.decorate.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
    public static void main(String[] args) throws IOException {
        int c;
        try{
            InputStream in = new LowerCaseInputStream(new File("C:/Users/a/Desktop/inputStreamTest.txt"));
            while ((c = in.read()) >= 0){
                System.out.print((char) c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
