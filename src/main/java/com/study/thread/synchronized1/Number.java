package com.study.thread.synchronized1;

/**
 * @author wenqianqian
 */
public class Number implements Runnable{

    private int i = 0;

    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notify();
                if(i < 100){
                    i++;
                    System.out.println(Thread.currentThread().getName()+"---"+i);
                }else{
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
