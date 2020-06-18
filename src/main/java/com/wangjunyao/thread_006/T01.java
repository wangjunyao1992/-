package com.wangjunyao.thread_006;

import java.util.concurrent.TimeUnit;

/**
 * һ��ͬ���������Ե�������һ��ͬ��������
 * һ���߳��Ѿ�ӵ��ĳ������������ٴ�������Ȼ��õ��ö������
 *
 * Ҳ����˵synchronized��õ����ǿ������
 */
public class T01 {

    public synchronized void method1(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
        System.out.println("method1");
    }

    public synchronized void method2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2");
    }

    public static void main(String[] args) {
        T01 t01 = new T01();
        t01.method1();
    }

}
