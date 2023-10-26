package com.study.test;

import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//        System.out.println(sdf.format(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());
    }
}
