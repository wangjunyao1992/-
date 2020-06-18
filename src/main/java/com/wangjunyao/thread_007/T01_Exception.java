package com.wangjunyao.thread_007;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果发生异常，默认情况锁会被释放（未捕获异常时），
 * 所以在并发处理过程中，有异常要多加小心，不然可能发生不一致的情况。
 *
 * 要想不被释放，可以在进行catch
 *
 * 例如：在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 *       在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据。
 *       因此要非常小心的处理同步业务逻辑中的异常
 */
public class T01_Exception {

    private int count = 0;

    public synchronized void method(){
        System.out.println(Thread.currentThread().getName() + " 开始执行");
        for (int i = 0; i < 10; i++) {
            count++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            if(i == 5) {
                /*
                 * 此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                 */
                int a = 1/0;
            }
        }
    }

    public static void main(String[] args) {
        T01_Exception t01_exception = new T01_Exception();
        new Thread(t01_exception::method).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t01_exception::method).start();
    }

}
