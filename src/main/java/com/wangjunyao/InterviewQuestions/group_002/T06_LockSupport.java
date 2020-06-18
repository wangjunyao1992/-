package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class T06_LockSupport {

    private volatile static List<Object> list = new ArrayList<>(10);

    //private static List<Object> list = Collections.synchronizedList(new ArrayList<>(10));

    private static Thread thread1 = null;

    private static Thread thread2 = null;

    public void add(Object obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T06_LockSupport t03_lockSupport = new T06_LockSupport();
        thread1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10000; i++) {
                t03_lockSupport.add(new Object());
                if(i == 9999) {
                    System.out.println("add " + t03_lockSupport.size());
                    LockSupport.unpark(thread2);
                    LockSupport.park();
                }
            }
        });

        thread2 = new Thread(() -> {
            System.out.println("t2启动");
            LockSupport.park();
            System.out.println("t2阻塞结束");
            for (int i = 0; i < 10; i++) {
                System.out.println("t2 结束" + t03_lockSupport.size());
            }
        });

        thread1.start();
        thread2.start();

    }

}
