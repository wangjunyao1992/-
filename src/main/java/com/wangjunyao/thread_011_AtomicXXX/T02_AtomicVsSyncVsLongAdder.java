package com.wangjunyao.thread_011_AtomicXXX;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 */
public class T02_AtomicVsSyncVsLongAdder {

    private static int threadCount = 1000;

    private static int count = 100000;

    private static int count1 = 0;

    private static AtomicInteger count2 = new AtomicInteger(0);

    private static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    synchronized (lock){
                        count1++;
                    }
                }
            });
        }

        Long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Synchronized : " + count1 + ", time : " + (end - start));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    count2.incrementAndGet();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("AtomicInteger : " + count2 + ", time : " + (end - start));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("LongAdder : " + count2 + ", time : " + (end - start));

    }

}
