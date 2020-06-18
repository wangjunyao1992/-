package com.wangjunyao.thread_001;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 什么是线程，线程是一个程序中不同的执行路径
 */
public class T01_WhatIsThread {

    /**
     * 线程类，继承Thread
     */
    private class T extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T");
            }
        }
    }

    public static void main(String[] args) {
        T t = new T01_WhatIsThread().new T();
        t.start();
        for(int i = 0; i < 10; i++){
            try {
                TimeUnit.MICROSECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
