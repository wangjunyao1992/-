package com.wangjunyao.thread_003;

import java.util.concurrent.TimeUnit;

/**
 * 对比上面一个小程序，分析一下这个程序的输出
 * 在for循环中new 对象，每个线程都有独立的对象
 */
public class T02 implements Runnable{

    private int count = 10;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args){

        for(int i=0; i<5; i++) {
            T02 t02 = new T02();
            new Thread(t02, "THREAD" + i).start();
        }
    }
}
