package com.wangjunyao.thread_012;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 是一个计数信号量，必须由获取它的线程释放
 * 常用于限制可以访问某些资源的线程数量，例如通过Semaphore限流
 */
public class T11_Semaphore {

    public static void main(String[] args) {
        //信号量，只允许两个线程同时访问
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }


}

