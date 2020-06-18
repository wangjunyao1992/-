package com.wangjunyao.thread_002;

public class T02_Sync {

    private static int count = 100;

    public static void method1(){//任何线程要执行下面代码，都必须先拿到T02_Sync class类对象的锁
        synchronized (T01_Sync.class){
            while (count > 0) {
                count--;
                System.out.println(Thread.currentThread().getName() + " amount = " + count);
            }
        }
    }

    public synchronized static void method2(){//这里等同于synchronized(T01_Sync.class)
        while (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + " amount = " + count);
        }
    }

    public static void mm() {
        synchronized(T02_Sync.class) { //考虑一下这里写synchronized(this)是否可以？
                                      // 编译不通过，static方法中不能使用this关键字
            count --;
        }
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                method1();
            }).start();
        }*/

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                method2();
            }).start();
        }
    }
}
