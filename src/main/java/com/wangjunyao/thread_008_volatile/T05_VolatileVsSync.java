package com.wangjunyao.thread_008_volatile;

import java.util.ArrayList;

/**
 * �Ա���һ�����򣬿�����synchronized�����
 * synchronized���Ա�֤�ɼ��Ժ�ԭ���ԣ�volatileֻ�ܱ�֤�ɼ���
 */
public class T05_VolatileVsSync {

    private /*volatile*/ static int count;

    private synchronized void method(){
        System.out.println("��start��" + Thread.currentThread().getName() + " count = " + count);
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println("��end��" + Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T05_VolatileVsSync t04 = new T05_VolatileVsSync();
        ArrayList<Thread> threads = new ArrayList<>(10);
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
