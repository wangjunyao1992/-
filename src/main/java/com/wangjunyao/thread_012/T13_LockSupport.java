package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是一个线程工具类，所有的方法都是静态方法，
 * 可以让线程在任意位置阻塞，也可以在任意位置唤醒。
 */
public class T13_LockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        LockSupport.unpark(t);
    }
}
