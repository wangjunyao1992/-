package com.wangjunyao.thread_002;

/**
 * synchronized关键字
 * 对某个对象加锁
 *
 * synchronized 能够实现
 *  -原子性（同步）
 *  -可见性
 * JMM关于synchronized的两条规定
 *  -线程解锁前，必须把共享变量的最新值刷新到主内存中
 *  -线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中
 *   重新读取最新的值（注意：加锁与解锁需要的是同一把锁）
 *
 * 这两点结合起来，就可以保证线程解锁前对共享变量的修改在下次加锁时对其他的线程可见，
 * 也就保证了线程之间共享变量的可见性。
 *
 */
public class T01_Sync extends Thread{

    private Object object = new Object();

    private int count = 100;

    private static int amount = 100;

    public void method1(){
        synchronized (object){//任何线程要执行下面代码，都必须先拿到object对象的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public void method2(){
        synchronized (this){//任何线程要执行下面代码，都必须先拿到this对象的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void method3(){//等同于在方法的代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    /**
     * 访问这个方法不需要锁
     */
    public void n(){
        count--;
    }

}
