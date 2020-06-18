package com.wangjunyao.thread_008_volatile;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile�����ܱ�֤����̹߳�ͬ�޸�running����ʱ�������Ĳ�һ�����⣬
 * Ҳ����˵volatile�������synchronized
 *
 * ��������ĳ��򣬲��������
 */
public class T04_VolatileNotSync {

    private volatile static int count;

    private void method(){
        System.out.println("��start��" + Thread.currentThread().getName() + " count = " + count);
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println("��end��" + Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T04_VolatileNotSync t04 = new T04_VolatileNotSync();
        List<Thread> threads = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t04::method, "thread" + i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(count);
    }

}
