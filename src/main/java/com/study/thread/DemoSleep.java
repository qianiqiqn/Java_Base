package com.study.thread;

/**
 * sleep()方法
 */
public class DemoSleep {
    public static void main(String[] args) {
        //        创建线程
        MyThread1 t01 = new MyThread1("黄固");
        MyThread1 t02 = new MyThread1("欧阳锋");
        MyThread1 t03 = new MyThread1("段智兴");
        MyThread1 t04 = new MyThread1("洪七公");

        //开启线程
        t01.start();
        t02.start();
        t03.start();
        t04.start();
    }
}
class MyThread1 extends Thread{
    public MyThread1() {
    }

    public MyThread1(String name) {
        super(name);
    }

    /**
     * 重点：run()当中的异常不能throws，只能try catch
     * 因为run()方法在父类中没有抛出任何异常，子类不能比父类抛出更多的异常。
     */
    @Override
    public void run() {
        for (int i = 1; i < 3; i++) {
            System.out.println(this.getName() + "正在打出第 - " + i + "招");

            try {
                //让当前正在执行的线程睡眠指定毫秒数
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
