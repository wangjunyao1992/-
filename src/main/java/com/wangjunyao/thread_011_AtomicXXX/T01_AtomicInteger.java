package com.wangjunyao.thread_011_AtomicXXX;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */
public class T01_AtomicInteger {

    private CountDownLatch latch0 = new CountDownLatch(10);

    private volatile int count0 = 0;

    private CountDownLatch latch1 = new CountDownLatch(10);

    private AtomicInteger count1 = new AtomicInteger(0);

    private /*synchronized*/ void method1(){
        for (int i = 0; i < 10000; i++) {
            count0++;
        }
        latch0.countDown();
    }

    private void method2(){
        for (int i = 0; i < 10000; i++) {
            count1.incrementAndGet();
        }
        latch1.countDown();
    }

    public static void main(String[] args) {
        T01_AtomicInteger t01_atomicInteger = new T01_AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(t01_atomicInteger::method1).start();
        }

        try {
            t01_atomicInteger.latch0.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count0 = " + t01_atomicInteger.count0);

        for (int i = 0; i < 10; i++) {
            new Thread(t01_atomicInteger::method2).start();
        }

        try {
            t01_atomicInteger.latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count1 = " + t01_atomicInteger.count1);
    }
}
