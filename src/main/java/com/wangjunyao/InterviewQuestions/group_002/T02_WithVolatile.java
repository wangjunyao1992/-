package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 *
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ����
 * ���ң������if �� break֮�䱻����̴߳�ϣ��õ��Ľ��Ҳ����ȷ��
 * ����ô���أ�
 */
public class T02_WithVolatile {

    //private volatile List<Object> list = new ArrayList<>(10);

    //private volatile List<Object> list = new LinkedList<>();

    private List<Object> list = Collections.synchronizedList(new ArrayList<>());

    public void add(Object obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T02_WithVolatile t02_withVolatile = new T02_WithVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t02_withVolatile.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true){
                System.out.println("size " + t02_withVolatile.size());
                if (t02_withVolatile.size() == 5) break;
            }
        }).start();
    }

}
