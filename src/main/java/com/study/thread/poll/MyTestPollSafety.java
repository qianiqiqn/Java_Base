package com.study.thread.poll;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenqianqian
 */
public class MyTestPollSafety {
    static int a=0;
    static int count=2000;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        //闭锁 在一些条件下可放开  参数:加多少把锁
        CountDownLatch countDownLatch=new CountDownLatch(count);
        for(int i=0;i<count;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (MyTestPollSafety.class) {
                        a++;
                        //解一把锁
                        countDownLatch.countDown();
                    }
                }
            });
        }
        service.shutdown();
        //会进入阻塞状态  什么时候把锁全解了   阻塞状态才会解除
        countDownLatch.await();
        System.out.println(a);
        //2000
    }

}
