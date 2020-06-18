package com.wangjunyao.thread_012;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ѭ��դ������ѭ�����õ�����
 *
 * ���������ǻ��������̶߳��ȴ���ɺ�Ż������һ���ж�
 *
 */
public class T07_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("���ˣ�����"));
        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("���ˣ�����");
            }
        });*/
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("��ʼ");
                    barrier.await();
                    System.out.println("����");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
