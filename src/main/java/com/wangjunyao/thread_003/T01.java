package com.wangjunyao.thread_003;

/**
 * 分析一下这个程序的输出
 * 1) volatile 和 synchronized都注释   输出无序
 * 2）只放开 volatile 输出比上个版本有序，但整体还是乱的    volatile只保证了可见性，不保证原子性
 * 3) 只放开synchronized输出有序
 * 4）两个都放开，是有序的
 */
public class T01 implements Runnable{

    private volatile int count = 100;

    private Object object = new Object();

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    /*public void run() {//和上面的效果一样
        synchronized (object){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }*/

   /* public void run(){//不能保证有序输出
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }*/

    public static void main(String[] args) {
        T01 t = new T01();
        for(int i=0; i<100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}
