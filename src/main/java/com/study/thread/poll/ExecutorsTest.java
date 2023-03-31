package com.study.thread.poll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService threadPoll = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            //如果不睡眠，那么第一个执行完的线程无法及时成为空闲线程，那么线程池就会让一个新的线程执行
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //每次循环都会开启一个线程
            threadPoll.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在被执行！~");
                }
            });
        }
        threadPoll.shutdown();//关闭线程池
        //线程池是无限大，当执行当前任务时，上一个任务已经完成，会重复执行上一个任务的线程，而不是每次使用新的线程
    }

}
