package com.wangjunyao.thread_001;

/**
 * sleep();
 * yield();
 * join();
 */
public class T03_Sleep_Yield_join {

    public static void sleepTest(){
        for (int i = 0; i < 100; i++) {
            System.out.println("sleep: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void yieldTest(){
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("yieldTest1: " + i);
                if(i % 10 == 0)
                    Thread.yield();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("yieldTest2: " + i);
                if(i % 10 == 0)
                    Thread.yield();
            }
        }).start();
    }

    public static void joinTest(){
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("thread1: " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("thread2: " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        //sleepTest();
        //yieldTest();
        joinTest();
    }
}
