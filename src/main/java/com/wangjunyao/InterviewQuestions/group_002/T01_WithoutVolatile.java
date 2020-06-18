package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
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
        //线程1
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

        //线程2
        new Thread(() -> {
            while (true){
                System.out.println("size " + t01_withoutVolatile.size());
                if (t01_withoutVolatile.size() == 5){
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "T2").start();
    }

}
