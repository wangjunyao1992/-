package com.wangjunyao.thread_012;

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
 *
 * ReentrantLock������ָ��Ϊ��ƽ��
 */
public class T05_ReentrantLock5 extends Thread {

    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(i + " "+ Thread.currentThread().getName() + "�����");
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
