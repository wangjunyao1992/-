package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 *
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 *
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么？
 *
 * 答：在t1中notify，t1并没有调用wait方法，没有释放锁，
 *     因此，t1会继续执行，直到结束；所以t2会等到t1结束后，才能拿到锁，得以执行
 *
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 */
public class T04_NotifyFreeLock {

    private static final Object lock = new Object();

    private static boolean flag = false;

    private List<Object> list = new ArrayList<>(10);

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        T03_NotifyHoldingLock t03_notifyHoldingLock = new T03_NotifyHoldingLock();

        Thread thread1 = new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    t03_notifyHoldingLock.add(new Object());
                    System.out.println("add " + i);
                    if(i == 5){
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1");

        Thread thread2 = new Thread(() -> {
            System.out.println("t2启动");
            synchronized (lock){
                if (t03_notifyHoldingLock.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("size " + t03_notifyHoldingLock.size());
                System.out.println("t2结束");
                lock.notifyAll();
            }
        }, "t2");

        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.start();

    }

}
