package com.study.test;

import com.study.generics.CommonMethod;
import com.study.generics.CommonResult;

import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//        System.out.println(sdf.format(System.currentTimeMillis()));


        CommonResult commonResult = CommonResult.success("测试数据");

        System.out.println(CommonMethod.method1(commonResult,CommonResult.class).getResultMsg());


        System.out.println(CommonMethod.method1("字符串数据。",String.class));
    }
}
