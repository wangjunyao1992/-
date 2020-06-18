package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * ʹ��ArrayList������߳��򼯺���������ݣ�
 * �����߳̽����󣬶�ȡ����size������size��һ���������߳�������ݵĺ�
 */
public class T01_ListConcurrentIssuesAdd {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        List<String> list = new ArrayList<>(10000);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    list.add(UUID.randomUUID().toString());
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(list.size());
    }
}
