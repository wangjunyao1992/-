package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 *
 */
public class T05_MyContainer3<T> {

    private List<T> list = new ArrayList<>(10);

    private static final int MAX_LENGTH = 10;

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    private Condition producerCondition = lock.newCondition();

    private Condition consumerCondition = lock.newCondition();

    public void producer(T t){
        try {
            lock.lock();
            while (this.count == T05_MyContainer3.MAX_LENGTH){
                producerCondition.await();
            }
            list.add(t);
            System.out.println(Thread.currentThread().getName() + "  " + t.toString());
            count++;
            consumerCondition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //消费者
    public T consumer(){
        T t = null;
        try {
            lock.lock();
            while (this.count == 0){
                consumerCondition.await();
            }
            t = list.remove(count - 1);
            System.out.println(Thread.currentThread().getName() + "  " + t.toString());
            count--;
            producerCondition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        T05_MyContainer3<String> t05_myContainer3 = new T05_MyContainer3();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t05_myContainer3.producer("  " + j);
                }
            }, "【生产者】" + i).start();
        }
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                t05_myContainer3.consumer();
            }, "【消费者】" + i).start();
        }
    }
}
