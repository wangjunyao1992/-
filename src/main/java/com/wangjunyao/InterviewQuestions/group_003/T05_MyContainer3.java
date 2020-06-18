package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 *
 * ʹ��wait��notify/notifyAll��ʵ��
 *
 * ʹ��Lock��Condition��ʵ��
 * �Ա����ַ�ʽ��Condition�ķ�ʽ���Ը��Ӿ�ȷ��ָ����Щ�̱߳�����
 *
 */
public class T05_MyContainer3<T> {

    private List<T> list = new ArrayList<>(10);

    private static final int MAX_LENGTH = 10;

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    private Condition producerCondition = lock.newCondition();

    private Condition consumerCondition = lock.newCondition();

    public void producer(T t){
        try {
            lock.lock();
            while (this.count == T05_MyContainer3.MAX_LENGTH){
                producerCondition.await();
            }
            list.add(t);
            System.out.println(Thread.currentThread().getName() + "  " + t.toString());
            count++;
            consumerCondition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //������
    public T consumer(){
        T t = null;
        try {
            lock.lock();
            while (this.count == 0){
                consumerCondition.await();
            }
            t = list.remove(count - 1);
            System.out.println(Thread.currentThread().getName() + "  " + t.toString());
            count--;
            producerCondition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        T05_MyContainer3<String> t05_myContainer3 = new T05_MyContainer3();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t05_myContainer3.producer("  " + j);
                }
            }, "�������ߡ�" + i).start();
        }
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                t05_myContainer3.consumer();
            }, "�������ߡ�" + i).start();
        }
    }
}
