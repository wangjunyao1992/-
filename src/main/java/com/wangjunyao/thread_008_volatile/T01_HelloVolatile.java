package com.wangjunyao.thread_008_volatile;

import java.util.concurrent.TimeUnit;

/**
 * olatile�ؼ��֣�ʹһ�������ڶ���̼߳�ɼ�
 *
 * A B�̶߳��õ�һ��������JavaĬ����A B�߳��и�����һ��copy��
 * �������B�߳��޸��˸ñ�������A�߳�δ��֪��
 *
 * ������Ĵ����У�running�Ǵ����ڶ��ڴ��t01�����У�
 * ���߳�thread1��ʼ���е�ʱ�򣬻��runningֵ���ڴ���
 * ����thread1�̵߳Ĺ������������й�����ֱ��ʹ�����copy��
 * ������ÿ�ζ�ȥ��ȡ���ڴ棬�����������߳��޸�running��ֵ֮��
 * thread1�̸߳�֪���������Բ���ֹͣ����
 *
 * ʹ��volatile������ǿ�������̶߳�ȥ���ڴ��ж�ȡrunning��ֵ��������ʹ�ù������е�copyֵ
 *
 * �����Ķ���ƪ���½��и���������
 *  http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile�����ܱ�֤����̹߳�ͬ�޸�running����ʱ�������Ĳ�һ�����⣬Ҳ����˵volatile�������synchronized
 *
 *
 * volatile�ܹ�ʵ��
 *  -�ܹ���֤volatile�����Ŀɼ���
 *  -���ܱ�֤volatile������ԭ����
 * volatile������ÿ�α��̷߳���ʱ����ǿ�ȴ����ڴ����ض��ñ�����ֵ��
 * �������������仯ʱ����ǿ���߳̽����µ�ֵˢ�µ����ڴ档�����κ�ʱ�̣�
 * ��ͬ���߳����ܿ����ñ���������ֵ��
 *
 * �ҵ���⣺���ǲ��ܱ�֤ÿ���߳���volatile�����仯��ǿ���ٶ�һ�Ρ�
 *
 */
public class T01_HelloVolatile {

    private /*volatile*/ boolean running = true;

    //�Ա�һ������volatile������£������������н��������
    public void method(){
        System.out.println("method start");
        while (running){}
        System.out.println("method end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t01 = new T01_HelloVolatile();
        new Thread(t01::method, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t01.running = false;
    }

}
