package com.wangjunyao.thread_009_lockOptimization;

import java.util.concurrent.TimeUnit;

/**
 * synchronized�Ż�
 * ͬ��������е����Խ��Խ��
 * �Ƚ�method1��method2
 */
public class FineCoarseLock {
    int count = 0;

    synchronized void method1() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ҵ���߼���ֻ�����������Ҫsync����ʱ��Ӧ�ø�������������
        count ++;

        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void method2() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ҵ���߼���ֻ�����������Ҫsync����ʱ��Ӧ�ø�������������
        //����ϸ���ȵ���������ʹ�߳�����ʱ���̣��Ӷ����Ч��
        synchronized(this) {
            count ++;
        }
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
