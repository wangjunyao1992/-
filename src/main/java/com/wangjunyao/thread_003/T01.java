package com.wangjunyao.thread_003;

/**
 * ����һ�������������
 * 1) volatile �� synchronized��ע��   �������
 * 2��ֻ�ſ� volatile ������ϸ��汾���򣬵����廹���ҵ�    volatileֻ��֤�˿ɼ��ԣ�����֤ԭ����
 * 3) ֻ�ſ�synchronized�������
 * 4���������ſ����������
 */
public class T01 implements Runnable{

    private volatile int count = 100;

    private Object object = new Object();

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    /*public void run() {//�������Ч��һ��
        synchronized (object){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }*/

   /* public void run(){//���ܱ�֤�������
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }*/

    public static void main(String[] args) {
        T01 t = new T01();
        for(int i=0; i<100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}
