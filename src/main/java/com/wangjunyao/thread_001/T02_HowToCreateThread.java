package com.wangjunyao.thread_001;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 如何创建线程
 * 继承Thread类，或者实现Runnable接口，在线程池中获取
 */
public class T02_HowToCreateThread {

    /**
     * 线程类，继承Thread
     */
    private class MyThread extends Thread{
        @Override
        public void run() {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("extends Thread");
        }
    }

    /**
     * 线程类，实现Runnable
     */
    private class MyRunnable implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.MICROSECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("implements Runnable");
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new T02_HowToCreateThread().new MyRunnable();
        MyThread myThread = new T02_HowToCreateThread().new MyThread();

        new Thread(myRunnable).start();
        myThread.start();
    }

}
