package com.study.thread.synchronized1;

/**
 * @author wenqianqian
 *
 * 两个线程交替打印1-100
 */
public class TestPrintNumber {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number,"线程1");
        Thread t2 = new Thread(number,"线程2");
        t1.start();
        t2.start();
    }

}
