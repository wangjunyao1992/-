package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，
 * 而且，如果在if 和 break之间被别的线程打断，得到的结果也不精确，
 * 该怎么做呢？
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
