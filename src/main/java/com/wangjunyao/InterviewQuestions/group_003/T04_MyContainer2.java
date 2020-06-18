package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;

/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 *
 * ʹ��wait��notify/notifyAll��ʵ��
 *
 * notify/notifyAll
 *
 * wait()ʹ��ǰ�߳�������ǰ���Ǳ����Ȼ������һ�����synchronized�ؼ���ʹ�á�
 * ���߳�ִ��wait()����ʱ�����ͷŵ�ǰ������Ȼ���ó�CPU������ȴ�״̬��
 * ֻ�е�notify/notifyAll()��ִ���ǣ��˻ػ���һ�����������ڵȴ�״̬���̣߳�Ȼ�����
 * ����ִ�У�ֱ��ִ����synchronized�����Ĵ��������;����wait()���ٴ��ͷ�����
 *
 * Ҳ����˵��notify/notifyAll()��ִ��ֻ�ǻ��ѳ�˯���̣߳������������ͷ�����
 * �����ͷ�Ҫ����������ִ������������ڱ���У�������ʹ����notify/notifyAll()��
 * �����˳��ٽ������Ի��������߳���������
 *
 * notify����ֻ����һ���ȴ�������̲߳�ʹ���߳̿�ʼִ�С���������ж���̵߳ȴ�
 * һ�������������ֻ�ỽ������һ���̣߳�ѡ���ĸ��߳�ȡ���ڲ���ϵͳ�Զ��̹߳����ʵ�֡�
 *
 * notifyAll�����ỽ�����еȴ�������̡߳�
 *
 */
public class T04_MyContainer2<T> {

    private List<T> list = new ArrayList<>(10);

    private static final int MAX_LENGTH = 10;

    private int count = 0;

    public void put(T t){
        list.add(t);
    }

    public int get(){
        return list.size();
    }

    //��������
    private Object producerLock = new Object();

    //������
    public void producer(T t){
        synchronized (producerLock){
            while (this.count == T04_MyContainer2.MAX_LENGTH){
                try {
                    producerLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            System.out.println("�������ߡ�" + Thread.currentThread().getName() + "  " + t.toString());
            count++;
            producerLock.notifyAll();
        }
    }

    //��������
    private Object consumerLock = new Object();

    //������
    public T consumer(){
        synchronized (consumerLock){
            while (this.count == 0){
                try {
                    consumerLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = this.list.remove(this.count - 1);
            System.out.println("�������ߡ�" + Thread.currentThread().getName() + "  " + t.toString());
            count--;
            consumerLock.notifyAll();
            return t;
        }
    }

    public static void main(String[] args) {
        T04_MyContainer2 t04_myContainer2 = new T04_MyContainer2();
        //�������߳�
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t04_myContainer2.producer("" + j);
                }
            }).start();
        }
        //�������߳�
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                t04_myContainer2.consumer();
            }).start();
        }
    }
}
