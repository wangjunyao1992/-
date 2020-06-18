package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
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
 * ����ͨ�Ź��̱ȽϷ���
 *
 * ʹ��Latch�����ţ����wait  notify������֪ͨ
 * �ô���ͨ�ŷ�ʽ�򵥣�ͬʱҲ����ָ���ȴ�ʱ��
 * ʹ��await��countdown�������wait��notify
 * CountDownLatch���漰��������count��ֵΪ0ʱ����ǰ�̼߳���ִ��
 * �����漰ͬ����ֻ�漰�߳�ͨ�ŵ�ʱ����synchronized + wait/notify���Ե�̫����
 * ��ʱӦ�ÿ���countdownlatch/cyclicbarrier/semaphore
 *
 */
public class T05_CountDownLatch {

    private static CountDownLatch latch = new CountDownLatch(1);

    private static List<Object> list = new ArrayList<>(10);

    public void add(Object obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch t05_countDownLatch = new T05_CountDownLatch();
        new Thread(() -> {
            System.out.println("t1��ʼ");
            for (int i = 0; i < 10; i++) {
                t05_countDownLatch.add(new Object());
                if(i == 4){
                    latch.countDown();
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();

        new Thread(() -> {
            System.out.println("t2��ʼ");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t05_countDownLatch.size());
            System.out.println("t2����");
        }).start();
    }

}
