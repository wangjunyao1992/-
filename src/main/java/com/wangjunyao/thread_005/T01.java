package com.wangjunyao.thread_005;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 *
 * 看业务需求，是否允许脏读
 */
public class T01 {

    private String name;

    private long balance;

    public T01(String name, long balance){
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(long balance){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            this.balance = balance;
        }
        System.out.println("【setBalance】name = " + this.name + ", balance = " + this.balance);
    }

    public long getBalance(){
        synchronized (this){
            return this.balance;
        }
    }

    public static void main(String[] args) {
        T01 t01 = new T01("王小五", 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                t01.setBalance(100000);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("【getBalance】" + t01.getBalance());
            }
        }).start();
    }


}
