package com.wangjunyao.thread_004;

import java.util.concurrent.TimeUnit;

/**
 * 同步和非同步方法是否可以同时调用？
 * 是可以调用的，互不影响，非同步方法不需要申请锁
 */
public class T01 {

    public synchronized void method1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " method1");
    }

    public void method2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " method2");
    }

    public static void main(String[] args) {
        T01 t01 = new T01();
        new Thread(t01::method1).start();
        new Thread(t01::method2).start();
    }

}
