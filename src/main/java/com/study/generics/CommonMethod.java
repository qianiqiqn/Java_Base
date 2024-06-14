package com.study.generics;

import com.alibaba.fastjson2.JSONObject;

import java.text.SimpleDateFormat;

/**
 * @author wenqianqian
 */
public class CommonMethod {

    public static void main(String[] args) {

        CommonResult commonResult = CommonResult.success("测试数据");

        System.out.println(CommonMethod.method1(commonResult,CommonResult.class).getResultMsg());


        System.out.println(CommonMethod.method1("字符串数据。",String.class));
    }


    public static <T> T method1(T t1,Class<? extends T> t2) {
        if(t1 instanceof String){
            return t1;
        }
        System.out.println("JSONObject: ");
        return JSONObject.parseObject(JSONObject.toJSONString(t1),t2);
    }

}
