package com.wangjunyao.thread_008_volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile 修饰引用类型（包括数组），只能保证引用本身的可见性，
 * 不能保证内部属性的可见性
 *
 * running属性没有被volatile修饰，主线程修改running的值，并没有使thread1线程停止运行
 */
public class T02_VolatileReference1 {
    private boolean running = true;
    private static volatile T02_VolatileReference1 t02 = new T02_VolatileReference1();

    public void method(){
        System.out.println("method start");
        while (running){}
        System.out.println("method end");
    }

    public static void main(String[] args) {
        new Thread(t02::method, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t02.running = false;
    }
}
