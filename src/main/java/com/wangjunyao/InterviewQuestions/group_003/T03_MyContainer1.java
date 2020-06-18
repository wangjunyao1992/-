package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;

/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 *
 * ʹ��wait��notify/notifyAll��ʵ��
 *
 * ����ʵ�ֺܿ��ܻ���������߻��������ߣ�������������߶�����wait���޷����������ߣ������ѭ��
 * ͬ��������Ҳ�ỽ�������ߣ������ѭ��
 *
 *
 *  ΪʲôҪʹ��whileѭ���ж������ջ������أ�
 * �������߳�A���ж�if����Ϊ�գ���������if������ڽ����˵ȴ���
 * �������������߳�BҲ�ж�if����Ϊ�գ�Ҳ���뵽if������ڽ����˵ȴ���
 * ��ʱ���������߳�C������һ����Ʒ���Ȼ������������߳�A��A���Ѻ��if������ڻָ�ִ�У�
 * Ȼ��ֱ������һ����Ʒ����ʱ�����գ������������ܻ������������߳�B��
 * �����������߳�B�ղ�Ҳ���뵽��if������У��������ж�һ��if����Ϊ�գ���
 * ��ʱֱ�ӴӴ�����лָ�ִ�У�������Ʒʱ�����������и���û����Ʒ�������ѡ�
 * �������������while�����жϵĻ����ڻ����߳�ʱ����Ȼ���ж������Ƿ�Ϊ�ա����ܷ�ֹ����
 *
 */
public class T03_MyContainer1<T> {

    private final List<T> list = new ArrayList<>(10);

    //������󳤶�
    private static final int MAX_LENGTH = 10;

    //��ǰ��������
    private int count = 0;

    //������
    public void producer(T t) throws InterruptedException {
        synchronized (this){
            /*
             * ����Ϊʲô��while��������if��
             *
             * ��ǰ�߳��ж�����������Ϊ���ֵ����ʼ������
             * �������Ѻ�����ǡ����������̡߳������˸��̣߳����������ᳬ��
             * �趨���������             *
             */
            /*if(count == T02_MyContainer1.MAX_LENGTH){
                this.wait();
            }*/
            while (count == T03_MyContainer1.MAX_LENGTH){
                this.wait();
            }
            list.add(t);
            System.out.println("producerSize = " + list.size());
            System.out.println(Thread.currentThread().getName() + "��" + t.toString());
            count++;
            notifyAll();
        }
    }

    //������
    public T consumer() throws InterruptedException {
        synchronized (this){
            /*
             * ����Ϊʲô��while��������if��
             *
             * ��ǰ�߳��ж�����������Ϊ0����ʼ������
             * �������Ѻ�����ǡ��������̡߳������˸��̣߳�
             * list.remove �����out-of-bounds�쳣             *
             */
            /*if(count == 0){
                this.wait();
            }*/
            while (count == 0){
                this.wait();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("consumerSize = " + list.size());
            T t = list.remove(list.size() - 1);
            System.out.println(Thread.currentThread().getName() + "��" + t.toString());
            count--;
            notifyAll();
            return t;
        }
    }

    public static void main(String[] args) {
        T03_MyContainer1 t02_myContainer1 = new T03_MyContainer1();
        //�������߳�
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    try {
                        t02_myContainer1.producer("  " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "������" + i).start();
        }

        //�������߳�
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    t02_myContainer1.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "������" + i).start();
        }
    }

}
