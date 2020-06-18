package com.wangjunyao.thread_012;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock�������synchronized
 * ����������m1����this,ֻ��m1ִ����ϵ�ʱ��,m2����ִ��
 * �����Ǹ�ϰsynchronized��ԭʼ������
 */
public class T01_ReentrantLock1 {

    private synchronized void method1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 3) method2();
        }
    }

    private synchronized void method2(){
        System.out.println(Thread.currentThread().getName()+ "  m2");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 t01_reentrantLock1 = new T01_ReentrantLock1();
        new Thread(t01_reentrantLock1::method1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t01_reentrantLock1::method2).start();
    }

}
