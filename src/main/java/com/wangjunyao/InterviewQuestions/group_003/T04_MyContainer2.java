package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * notify/notifyAll
 *
 * wait()使当前线程阻塞，前提是必须先获得锁，一般配合synchronized关键字使用。
 * 当线程执行wait()方法时，会释放当前的锁，然后让出CPU，进入等待状态。
 * 只有当notify/notifyAll()被执行是，菜回唤醒一个或多个正处于等待状态的线程，然后继续
 * 往下执行，直到执行完synchronized代码块的代码或是中途遇到wait()，再次释放锁。
 *
 * 也就是说，notify/notifyAll()的执行只是唤醒沉睡的线程，而不会立即释放锁，
 * 锁的释放要看代码块具体执行情况。所以在编程中，尽量在使用了notify/notifyAll()后
 * 立即退出临界区，以唤醒其他线程让其获得锁
 *
 * notify方法只唤醒一个等待对象的线程并使该线程开始执行。所以如果有多个线程等待
 * 一个对象，这个方法只会唤醒其中一个线程，选择哪个线程取决于操作系统对多线程管理的实现。
 *
 * notifyAll方法会唤醒所有等待对象的线程。
 *
 */
public class T04_MyContainer2<T> {

    private List<T> list = new ArrayList<>(10);

    private static final int MAX_LENGTH = 10;

    private int count = 0;

    public void put(T t){
        list.add(t);
    }

    public int get(){
        return list.size();
    }

    //生产者锁
    private Object producerLock = new Object();

    //生产者
    public void producer(T t){
        synchronized (producerLock){
            while (this.count == T04_MyContainer2.MAX_LENGTH){
                try {
                    producerLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            System.out.println("【生产者】" + Thread.currentThread().getName() + "  " + t.toString());
            count++;
            producerLock.notifyAll();
        }
    }

    //消费者锁
    private Object consumerLock = new Object();

    //消费者
    public T consumer(){
        synchronized (consumerLock){
            while (this.count == 0){
                try {
                    consumerLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = this.list.remove(this.count - 1);
            System.out.println("【消费者】" + Thread.currentThread().getName() + "  " + t.toString());
            count--;
            consumerLock.notifyAll();
            return t;
        }
    }

    public static void main(String[] args) {
        T04_MyContainer2 t04_myContainer2 = new T04_MyContainer2();
        //生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t04_myContainer2.producer("" + j);
                }
            }).start();
        }
        //消费者线程
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                t04_myContainer2.consumer();
            }).start();
        }
    }
}
