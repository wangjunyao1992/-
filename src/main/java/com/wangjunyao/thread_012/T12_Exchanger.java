package com.wangjunyao.thread_012;

import java.util.concurrent.Exchanger;

/**
 * Exchanger�������������߳�֮�佻�����ݵķ�װ�����࣬
 * ����һ���߳������һ���������������һ���߳̽������ݣ�
 * ���һ�����ó����ݵ��̻߳�һֱ�ȴ��ڶ����̣߳�ֱ���ڶ����߳�
 * �������ݵ���ʱ���ܱ˴˽�����Ӧ���ݡ�
 */
public class T12_Exchanger {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String str = "T1";
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + str);
        }, "t1").start();

        new Thread(()->{
            String str = "T2";
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + str);

        }, "t2").start();
    }

}
