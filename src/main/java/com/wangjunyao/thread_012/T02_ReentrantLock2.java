package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock�����滻synchronized
 * ʹ��ReentrantLock�������ͬ���Ĺ���
 * ��Ҫע�����ReentrantLock��Ҫ�ֶ��ͷ���
 * ʹ��synchronized�������쳣JVM���Զ��ͷ�����
 * ����Lock�����ֶ��ͷ�����������finally���ֶ��ͷ���
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
