package com.study.thread.safety;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    /**
     * 账号
     */
    private String actno;
    /**
     * 余额
     */
    private double balance;

    public Account() {
    }

    public Account(String actno, double balance) {
        this.actno = actno;
        this.balance = balance;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    /**
     * 取款的方法,不安全
     * @param money 金额
     */
    public void withdraw(double money){
        // t1和t2并发这个方法。。。。（t1和t2是两个栈。两个栈操作堆中同一个对象。）
        // 取款之前的余额
        // 10000
        double before = this.getBalance();
        // 取款之后的余额
        double after = before - money;
        // 在这里模拟一下网络延迟，100%会出现问题
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 更新余额
        // 思考：t1执行到这里了，但还没有来得及执行这行代码，t2线程进来withdraw方法了。此时一定出问题。
        this.setBalance(after);
    }

    /**
     * 使用synchronized修饰方法，线程安全
     * @param money 金额
     */
    public synchronized void withdrawSafe(double money) {
        // 10000
        double before = this.getBalance();
        // 取款之后的余额
        double after = before - money;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 更新余额
        this.setBalance(after);
    }


    /**
     * 定义Lock类型的锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 使用Lock,线程安全
     *   应用场景不同，不一定要在同一个方法中进行解锁，如果在当前的方法体内部没有满足解锁需求时，
     *   可以将lock引用传递到下一个方法中，当满足解锁需求时进行解锁操作，方法比较灵活。
     * @param money 金额
     */
    public void withdrawLock(double money) {
        // t1和t2并发这个方法。。。。（t1和t2是两个栈。两个栈操作堆中同一个对象。）
        // 取款之前的余额
        //上锁
        try {
            lock.lock();
            // 10000
            double before = this.getBalance();
            // 取款之后的余额
            double after = before - money;
            // 在这里模拟一下网络延迟，100%会出现问题
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 更新余额
            // 思考：t1执行到这里了，但还没有来得及执行这行代码，t2线程进来withdraw方法了。此时一定出问题。
            this.setBalance(after);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }

    }

}
