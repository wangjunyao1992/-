package com.wangjunyao.thread_006;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，
 * 一个线程已经拥有某个对象的锁，再次申请任然会得到该对象的锁
 *
 * 也就是说synchronized获得的锁是可重入的
 */
public class T01 {

    public synchronized void method1(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
        System.out.println("method1");
    }

    public synchronized void method2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2");
    }

    public static void main(String[] args) {
        T01 t01 = new T01();
        t01.method1();
    }

}
