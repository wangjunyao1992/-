package com.wangjunyao.thread_003;

import java.util.concurrent.TimeUnit;

/**
 * �Ա�����һ��С���򣬷���һ�������������
 * ��forѭ����new ����ÿ���̶߳��ж����Ķ���
 */
public class T02 implements Runnable{

    private int count = 10;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args){

        for(int i=0; i<5; i++) {
            T02 t02 = new T02();
            new Thread(t02, "THREAD" + i).start();
        }
    }
}
