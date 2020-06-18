package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock�����滻synchronized
 * ʹ��ReentrantLock�������ͬ���Ĺ���
 * ��Ҫע����ǣ�ReentrantLock��Ҫ�ֶ��ͷ���
 * ʹ��synchronized�������쳣JVM���Զ��ͷ���
 * ��Lock��Ҫ�ֶ��ͷ�����������finally���ͷ���
 * ʹ��ReentrantLock���Խ��С�����������
 *
 * tryLock()�������з���ֵ�ģ�����ʾ�������Ի�ȡ���������ȡ�ɹ����򷵻�true��
 * �����ȡʧ�ܣ������ѱ������̻߳�ȡ�����򷵻�false���������������ζ�������
 * ���ء����ò�����ʱ����һֱ������
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
