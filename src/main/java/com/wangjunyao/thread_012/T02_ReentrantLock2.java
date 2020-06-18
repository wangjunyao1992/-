package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock用于替换synchronized
 * 使用ReentrantLock可以完成同样的功能
 * 需要注意的是ReentrantLock需要手动释放锁
 * 使用synchronized，遇到异常JVM是自动释放锁，
 * 但是Lock必须手动释放锁，经成在finally中手动释放锁
 */
public class T02_ReentrantLock2 {

    Lock lock = new ReentrantLock();

    private void method1(){
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
                if (i == 5) method2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " method2");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T02_ReentrantLock2 t02_reentrantLock2 = new T02_ReentrantLock2();
        new Thread(t02_reentrantLock2::method1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t02_reentrantLock2::method2).start();
    }

}
