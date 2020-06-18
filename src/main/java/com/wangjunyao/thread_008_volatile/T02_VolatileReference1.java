package com.wangjunyao.thread_008_volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile �����������ͣ��������飩��ֻ�ܱ�֤���ñ���Ŀɼ��ԣ�
 * ���ܱ�֤�ڲ����ԵĿɼ���
 *
 * running����û�б�volatile���Σ����߳��޸�running��ֵ����û��ʹthread1�߳�ֹͣ����
 */
public class T02_VolatileReference1 {
    private boolean running = true;
    private static volatile T02_VolatileReference1 t02 = new T02_VolatileReference1();

    public void method(){
        System.out.println("method start");
        while (running){}
        System.out.println("method end");
    }

    public static void main(String[] args) {
        new Thread(t02::method, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t02.running = false;
    }
}
