package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
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
 */
public class T04_ReentrantLock4 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.lock();

            }
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            }finally {
                lock.unlock();
            }
        });

        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt(); //打断线程2的等待

    }

}
