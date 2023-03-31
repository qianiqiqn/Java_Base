package com.study.thread.poll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTestPoll {
    //定义静态变量
    static int a=0;
    static int count=2000;
    public static void main(String[] args) {
        //创建线程池
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<count;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    a++;
                }
            });
        }
        //关闭线程池
        service.shutdown();
        System.out.println(a);
        //1987
    }

}
