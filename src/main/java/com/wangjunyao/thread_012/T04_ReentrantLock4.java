package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock�����滻synchronized
 * ʹ��ReentrantLock�������ͬ���Ĺ���
 *
 * ��Ҫע����ǣ�ReentrantLock��Ҫ�ֶ��ͷ���
 * ʹ��synchronized�������쳣JVM���Զ��ͷ���
 * ����Lock��Ҫ�ֶ��ͷ�����������finally���ͷ���
 *
 * ʹ��ReentrantLock���Խ��С�����������
 * tryLock()�������з���ֵ�ģ�����ʾ�������Ի�ȡ���������ȡ�ɹ�
 * �򷵻�true�������ȡʧ�ܣ������ѱ������̻߳�ȡ���򷵻�false��
 * �������������ζ����������ء����ò�����ʱ����һֱ������
 *
 * ʹ��ReentrantLock�����Ե���lockInterruptibly���������Զ��߳�
 * interrupt����������Ӧ����һ���̵߳ȴ����Ĺ����У����Ա���ϡ�
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
        thread2.interrupt(); //����߳�2�ĵȴ�

    }

}
