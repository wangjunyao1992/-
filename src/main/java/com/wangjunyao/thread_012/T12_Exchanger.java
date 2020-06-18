package com.wangjunyao.thread_012;

import java.util.concurrent.Exchanger;

/**
 * Exchanger用于两个工作线程之间交换数据的封装工具类，
 * 就是一个线程在完成一定的事务后想与另一个线程交换数据，
 * 则第一个先拿出数据的线程会一直等待第二个线程，直到第二个线程
 * 拿着数据到来时才能彼此交换对应数据。
 */
public class T12_Exchanger {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String str = "T1";
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + str);
        }, "t1").start();

        new Thread(()->{
            String str = "T2";
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + str);

        }, "t2").start();
    }

}
