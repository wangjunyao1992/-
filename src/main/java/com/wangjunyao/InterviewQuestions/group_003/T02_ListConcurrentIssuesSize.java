package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ʹ��ArrayList�����������̣߳�һ���߳������������������ݣ�
 * ��һ���߳���ȡ������ϵ�sizeֵ��Ϊʲô�߳���ȡ����ֵһֱȡ�������µ�ֵ?
 */
public class T02_ListConcurrentIssuesSize {

    private List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        T02_ListConcurrentIssuesSize t03_list = new T02_ListConcurrentIssuesSize();
        new Thread(() -> {
            while (t03_list.list.size() == 0){
                /*
                 * ���ﲻ��ʹ��System.out.println���������в��ԣ���Ϊ�÷������̰߳�ȫ�ģ�
                 * ��ͬ����ʶ�������ᵼ��list�������´����ڴ��м���һ��
                 */
                //System.out.println(t03_list.list.size());
            }
            System.out.println("thread1 end =======");
        }, "thread1").start();
        TimeUnit.SECONDS.sleep(5);
        t03_list.list.add("123");
        System.out.println(t03_list.list.size());
    }

}
