package com.study.thread;


/**
 *  yield()
 *  暂停当前正在执行的线程对象，并执行其他线程
 *  yield()方法不是阻塞方法。让当前线程让位，让给其它线程使用。
 *  yield()方法的执行会让当前线程从“运行状态”回到“就绪状态”。
 *  注意：在回到就绪之后，有可能还会再次抢到。
 */
public class DemoYield {
    public static void main(String[] args) {
        //创建线程
        MyThread5 t01 = new MyThread5("线程01");
        MyThread5 t02 = new MyThread5("线程02");
        MyThread5 t03 = new MyThread5("线程03");

        //开启线程
        t01.start();
        t02.start();
        t03.start();
    }
}
class MyThread5 extends Thread{
    public MyThread5() {
    }

    public MyThread5(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if(2 == i){
                Thread.yield();
                //当循i环到30时，让线程让步
                //1、回到抢占队列中，又争夺到了执行权
                //2、回到抢占队列中，没有争夺到执行权
            }
            System.out.println(this.getName() + ":" + i);
        }
    }

}
