package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock用于替换synchronized
 * 使用ReentrantLock可以完成同样的功能
 * 需要注意的是，ReentrantLock需要手动释放锁
 * 使用synchronized，遇到异常JVM会自动释放锁
 * 但Lock需要手动释放锁，经常在finally中释放锁
 * 使用ReentrantLock可以进行“尝试锁定”
 *
 * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
 * 如果获取失败（即锁已被其他线程获取），则返回false，这个方法无论如何都会立即
 * 返回。在拿不到锁时不会一直阻塞。
 */
public class T03_ReentrantLock3 {

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
        boolean locked = false;
        try {
            locked = lock.tryLock();
            System.out.println("locked = " + locked);
            System.out.println(Thread.currentThread().getName() + " method2");
        }finally {
            if (locked)lock.unlock();
        }
    }

    public void method3(){
        boolean locked = false;
        try {
            locked = lock.tryLock(1L, TimeUnit.SECONDS);
            System.out.println("locked = " + locked);
            System.out.println(Thread.currentThread().getName() + " method3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked)lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T03_ReentrantLock3 t03_reentrantLock3 = new T03_ReentrantLock3();
        new Thread(t03_reentrantLock3::method1, "thread0").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t03_reentrantLock3::method2, "thread1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t03_reentrantLock3::method3, "thread2").start();
    }

}
