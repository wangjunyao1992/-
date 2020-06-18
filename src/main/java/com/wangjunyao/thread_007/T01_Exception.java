package com.wangjunyao.thread_007;

import java.util.concurrent.TimeUnit;

/**
 * ������ִ�й����У���������쳣��Ĭ��������ᱻ�ͷţ�δ�����쳣ʱ����
 * �����ڲ�����������У����쳣Ҫ���С�ģ���Ȼ���ܷ�����һ�µ������
 *
 * Ҫ�벻���ͷţ������ڽ���catch
 *
 * ���磺��һ��web app��������У����servlet�̹߳�ͬ����ͬһ����Դ����ʱ����쳣�������ʣ�
 *       �ڵ�һ���߳����׳��쳣�������߳̾ͻ����ͬ�����������п��ܻ���ʵ��쳣����ʱ�����ݡ�
 *       ���Ҫ�ǳ�С�ĵĴ���ͬ��ҵ���߼��е��쳣
 */
public class T01_Exception {

    private int count = 0;

    public synchronized void method(){
        System.out.println(Thread.currentThread().getName() + " ��ʼִ��");
        for (int i = 0; i < 10; i++) {
            count++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            if(i == 5) {
                /*
                 * �˴��׳��쳣���������ͷţ�Ҫ�벻���ͷţ��������������catch��Ȼ����ѭ������
                 */
                int a = 1/0;
            }
        }
    }

    public static void main(String[] args) {
        T01_Exception t01_exception = new T01_Exception();
        new Thread(t01_exception::method).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t01_exception::method).start();
    }

}
