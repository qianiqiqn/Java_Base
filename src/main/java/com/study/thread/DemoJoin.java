package com.study.thread;

/**
 * 线程调度模型
 *
 * 均分式调度模型：所有的线程轮流使用CPU的使用权，平均分配给每一个线程占用CPU的时间。
 *
 * 抢占式调度模型：优先让优先级高的线程使用CPU，如果线程的优先级相同，那么就会随机选择一个线程来执行，优先级高的占用CPU时间相对来说会高一点点。
 *
 * Java中JVM使用的就是抢占式调度模型
 *
 * getPriority():获取线程优先级
 *
 * setPriority：设置线程优先级
 *

 */
public class DemoJoin {
    public static void main(String[] args) {
        //创建线程
        MyThread t01 = new MyThread("线程01");
        MyThread t02 = new MyThread("线程02");
        MyThread t03 = new MyThread("线程03");
        //获取线程优先级，默认是5
        System.out.println(t01.getPriority());
        System.out.println(t02.getPriority());
        System.out.println(t03.getPriority());
        //设置线程优先级
        //低  - 理论上来讲，最后完成
        t01.setPriority(Thread.MIN_PRIORITY);
        //中
        t02.setPriority(Thread.NORM_PRIORITY);
        //高  - 理论上来讲，最先完成
        t03.setPriority(Thread.MAX_PRIORITY);
        //开启线程
        t01.start();
        t02.start();
        t03.start();
    }

}
