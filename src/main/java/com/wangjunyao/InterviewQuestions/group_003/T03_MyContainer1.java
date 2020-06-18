package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 这种实现很可能会造成生产者唤醒生产者，造成两个生产者都进入wait，无法唤醒消费者，造成死循环
 * 同理，消费者也会唤醒消费者，造成死循环
 *
 *
 *  为什么要使用while循环判断容器空或者满呢？
 * 消费者线程A先判断if条件为空，并进入了if代码块内进行了等待。
 * 接下来消费者线程B也判断if条件为空，也进入到if代码块内进行了等待。
 * 这时候生产者线程C生产了一个商品，先唤醒了消费者线程A，A唤醒后从if代码块内恢复执行，
 * 然后直接消费一个商品（此时容器空）。接下来可能唤醒了消费者线程B，
 * 由于消费者线程B刚才也进入到了if代码块中（不会再判断一次if容器为空），
 * 此时直接从代码块中恢复执行，消费商品时，发现容器中根本没有商品可以消费。
 * 所以如果条件用while进行判断的话，在唤醒线程时，依然会判断容器是否为空。才能防止出错。
 *
 */
public class T03_MyContainer1<T> {

    private final List<T> list = new ArrayList<>(10);

    //容器最大长度
    private static final int MAX_LENGTH = 10;

    //当前容器数量
    private int count = 0;

    //生产者
    public void producer(T t) throws InterruptedException {
        synchronized (this){
            /*
             * 想想为什么用while而不是用if？
             *
             * 当前线程判断容器中容量为最大值，开始阻塞，
             * 当被唤醒后，如果是【生产者者线程】唤醒了该线程，容器容量会超过
             * 设定的最大容量             *
             */
            /*if(count == T02_MyContainer1.MAX_LENGTH){
                this.wait();
            }*/
            while (count == T03_MyContainer1.MAX_LENGTH){
                this.wait();
            }
            list.add(t);
            System.out.println("producerSize = " + list.size());
            System.out.println(Thread.currentThread().getName() + "：" + t.toString());
            count++;
            notifyAll();
        }
    }

    //消费者
    public T consumer() throws InterruptedException {
        synchronized (this){
            /*
             * 想想为什么用while而不是用if？
             *
             * 当前线程判断容器中容量为0，开始阻塞，
             * 当被唤醒后，如果是【消费者线程】唤醒了该线程，
             * list.remove 会产生out-of-bounds异常             *
             */
            /*if(count == 0){
                this.wait();
            }*/
            while (count == 0){
                this.wait();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("consumerSize = " + list.size());
            T t = list.remove(list.size() - 1);
            System.out.println(Thread.currentThread().getName() + "：" + t.toString());
            count--;
            notifyAll();
            return t;
        }
    }

    public static void main(String[] args) {
        T03_MyContainer1 t02_myContainer1 = new T03_MyContainer1();
        //生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    try {
                        t02_myContainer1.producer("  " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者" + i).start();
        }

        //消费者线程
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    t02_myContainer1.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "消费者" + i).start();
        }
    }

}
