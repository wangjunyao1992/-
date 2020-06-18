package com.wangjunyao.thread_012;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock用于替换synchronized
 * 使用ReentrantLock可以完成同样的功能
 *
 * 需要注意的是，ReentrantLock需要手动释放锁
 * 使用synchronized，遇到异常JVM会自动释放锁
 * 但是Lock需要手动释放锁，经成在finally中释放锁
 *
 * 使用ReentrantLock可以进行“尝试锁定”
 * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功
 * 则返回true，如果获取失败（即锁已被其它线程获取）则返回false，
 * 这个方法无论如何都会立即返回。在拿不到锁时不会一直阻塞。
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程
 * interrupt方法做出响应，在一个线程等待锁的过程中，可以被打断。
 *
 * ReentrantLock还可以指定为公平锁
 */
public class T05_ReentrantLock5 extends Thread {

    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(i + " "+ Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 t05_reentrantLock5 = new T05_ReentrantLock5();
        Thread th1 = new Thread(t05_reentrantLock5);
        Thread th2 = new Thread(t05_reentrantLock5);
        th1.start();
        th2.start();
    }
}
