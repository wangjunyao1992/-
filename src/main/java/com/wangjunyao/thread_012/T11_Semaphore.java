package com.wangjunyao.thread_012;

import java.util.concurrent.Semaphore;

/**
 * Semaphore ��һ�������ź����������ɻ�ȡ�����߳��ͷ�
 * ���������ƿ��Է���ĳЩ��Դ���߳�����������ͨ��Semaphore����
 */
public class T11_Semaphore {

    public static void main(String[] args) {
        //�ź�����ֻ���������߳�ͬʱ����
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }


}

