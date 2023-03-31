package com.study.thread;

/**
 * 线程
 */
public class MyThread extends Thread{
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    /**
     * run方法是每个线程运行过程中都必须执行的方法
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：测试线程，run()方法调用。");
    }
}
