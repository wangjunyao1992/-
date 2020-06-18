package com.wangjunyao.thread_002;

/**
 * synchronized�ؼ���
 * ��ĳ���������
 *
 * synchronized �ܹ�ʵ��
 *  -ԭ���ԣ�ͬ����
 *  -�ɼ���
 * JMM����synchronized�������涨
 *  -�߳̽���ǰ������ѹ������������ֵˢ�µ����ڴ���
 *  -�̼߳���ʱ������չ����ڴ��й��������ֵ���Ӷ�ʹ�ù������ʱ��Ҫ�����ڴ���
 *   ���¶�ȡ���µ�ֵ��ע�⣺�����������Ҫ����ͬһ������
 *
 * ���������������Ϳ��Ա�֤�߳̽���ǰ�Թ���������޸����´μ���ʱ���������߳̿ɼ���
 * Ҳ�ͱ�֤���߳�֮�乲������Ŀɼ��ԡ�
 *
 */
public class T01_Sync extends Thread{

    private Object object = new Object();

    private int count = 100;

    private static int amount = 100;

    public void method1(){
        synchronized (object){//�κ��߳�Ҫִ��������룬���������õ�object�������
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public void method2(){
        synchronized (this){//�κ��߳�Ҫִ��������룬���������õ�this�������
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void method3(){//��ͬ���ڷ����Ĵ���ִ��ʱҪsynchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    /**
     * ���������������Ҫ��
     */
    public void n(){
        count--;
    }

}
