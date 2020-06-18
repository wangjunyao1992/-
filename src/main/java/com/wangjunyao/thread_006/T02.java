package com.wangjunyao.thread_006;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，
 * 再次申请的时候仍然会得到该对象的锁.也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 */
public class T02 {

    public synchronized void superMethod(){
        System.out.println("superMethod start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("superMethod end");
    }

    public static void main(String[] args) {
        TTT02 ttt02 = new TTT02();
        ttt02.childMethod();
    }

}

class TTT02 extends T02{
    public synchronized void childMethod(){
        System.out.println("childMethod start");
        super.superMethod();
        System.out.println("childMethod end");
    }
}
