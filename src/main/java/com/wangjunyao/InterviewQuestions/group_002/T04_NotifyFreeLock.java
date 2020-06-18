package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 *
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ��������ô���أ�
 *
 * ����ʹ��wait��notify������wait���ͷ�������notify�����ͷ���
 * ��Ҫע����ǣ��������ַ���������Ҫ��֤t2��ִ�У�Ҳ����������t2�����ſ���
 *
 * �Ķ�����ĳ��򣬲�����������
 * ���Զ���������������size=5ʱt2�˳�������t1����ʱt2�Ž��յ�֪ͨ���˳�
 * ��������Ϊʲô��
 *
 * ����t1��notify��t1��û�е���wait������û���ͷ�����
 *     ��ˣ�t1�����ִ�У�ֱ������������t2��ȵ�t1�����󣬲����õ���������ִ��
 *
 * notify֮��t1�����ͷ�����t2�˳���Ҳ����notify��֪ͨt1����ִ��
 */
public class T04_NotifyFreeLock {

    private static final Object lock = new Object();

    private static boolean flag = false;

    private List<Object> list = new ArrayList<>(10);

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        T03_NotifyHoldingLock t03_notifyHoldingLock = new T03_NotifyHoldingLock();

        Thread thread1 = new Thread(() -> {
            System.out.println("t1����");
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    t03_notifyHoldingLock.add(new Object());
                    System.out.println("add " + i);
                    if(i == 5){
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1");

        Thread thread2 = new Thread(() -> {
            System.out.println("t2����");
            synchronized (lock){
                if (t03_notifyHoldingLock.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("size " + t03_notifyHoldingLock.size());
                System.out.println("t2����");
                lock.notifyAll();
            }
        }, "t2");

        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.start();

    }

}
