package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У�
 * �߳�2ʵ�ּ��Ԫ�صĸ�������������5ʱ���߳�2������ʾ������
 */
public class T01_WithoutVolatile {

    List<Object> list = new ArrayList<>(10);

    public void add(Object obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile t01_withoutVolatile = new T01_WithoutVolatile();
        //�߳�1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t01_withoutVolatile.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T1").start();

        //�߳�2
        new Thread(() -> {
            while (true){
                System.out.println("size " + t01_withoutVolatile.size());
                if (t01_withoutVolatile.size() == 5){
                    break;
                }
            }
            System.out.println("t2 ����");
        }, "T2").start();
    }

}
