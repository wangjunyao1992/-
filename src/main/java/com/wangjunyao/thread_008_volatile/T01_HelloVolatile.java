package com.wangjunyao.thread_008_volatile;

import java.util.concurrent.TimeUnit;

/**
 * olatile关键字，使一个变量在多个线程间可见
 *
 * A B线程都用的一个变量，Java默认是A B线程中各保留一份copy，
 * 这样如果B线程修改了该变量，则A线程未必知道
 *
 * 在下面的代码中，running是存在于堆内存的t01对象中，
 * 当线程thread1开始运行的时候，会把running值从内存中
 * 读到thread1线程的工作区，在运行过程中直接使用这个copy，
 * 并不会每次都去读取堆内存，这样，当主线程修改running的值之后，
 * thread1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值，而不是使用工作区中的copy值
 *
 * 可以阅读这篇文章进行更深入的理解
 *  http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 *
 *
 * volatile能够实现
 *  -能够保证volatile变量的可见性
 *  -不能保证volatile变量的原子性
 * volatile变量在每次被线程访问时，都强迫从主内存中重读该变量的值，
 * 而当变量发生变化时，又强迫线程将最新的值刷新到主内存。这样任何时刻，
 * 不同的线程总能看到该变量的最新值。
 *
 * 我的理解：但是不能保证每个线程在volatile变量变化后，强制再读一次。
 *
 */
public class T01_HelloVolatile {

    private /*volatile*/ boolean running = true;

    //对比一下有无volatile的情况下，整个程序运行结果的区别
    public void method(){
        System.out.println("method start");
        while (running){}
        System.out.println("method end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t01 = new T01_HelloVolatile();
        new Thread(t01::method, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t01.running = false;
    }

}
