package com.wangjunyao.thread_012;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。
 *
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，
 * 计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的
 * 线程就可以继续执行了。
 */
public class T06_CountDownLatch {

    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }

    public static void usingCountDownLatch(){
        CountDownLatch latch = new CountDownLatch(100);
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int count = 0;
                for (int j = 0; j < 10000; j++) {
                    j++;
                }
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    public static void usingJoin(){
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int count = 0;
                for (int j = 0; j < 10000; j++) {
                    j++;
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }

}
