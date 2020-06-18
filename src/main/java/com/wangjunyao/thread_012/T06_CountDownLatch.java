package com.wangjunyao.thread_012;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch�ܹ�ʹһ���߳��ڵȴ�����һЩ�߳���ɸ��Թ���֮���ټ���ִ�С�
 *
 * ��ͨ��һ����������ʵ�ֵģ��������ĳ�ʼֵ���̵߳�������ÿ��һ���߳�ִ����Ϻ�
 * ��������ֵ��-1������������ֵΪ0ʱ����ʾ�����̶߳�ִ����ϣ�Ȼ���ڱ����ϵȴ���
 * �߳̾Ϳ��Լ���ִ���ˡ�
 */
public class T06_CountDownLatch {

    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }

    public static void usingCountDownLatch(){
        CountDownLatch latch = new CountDownLatch(100);
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int count = 0;
                for (int j = 0; j < 10000; j++) {
                    j++;
                }
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    public static void usingJoin(){
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int count = 0;
                for (int j = 0; j < 10000; j++) {
                    j++;
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }

}
