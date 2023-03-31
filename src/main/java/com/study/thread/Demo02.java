package com.study.thread;


/**
 * 多线程，实现Runnable接口
 */
public class Demo02 {
    public static void main(String[] args) {

        //将一个任务提取出来，让多个线程共同去执行
        MyRunnable myRun = new MyRunnable();
        //封装线程对象
        Thread t01 = new Thread(myRun, "线程01");
        Thread t02 = new Thread(myRun, "线程02");
        Thread t03 = new Thread(myRun, "线程03");
        //开启线程
        t01.start();
        t02.start();
        t03.start();
        //通过匿名内部类的方式创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " -内部类的方式- " + i);
                }
            }
        },"线程04").start();
    }
}

/**
 * 自定义线程类，实现Runnable接口
 * 这并不是一个线程类，是一个可运行的类，它还不是一个线程。
 */
class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -run()方法- " + i);
        }
    }
}

