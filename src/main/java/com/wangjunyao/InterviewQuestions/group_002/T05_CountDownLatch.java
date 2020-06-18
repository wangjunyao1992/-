package com.wangjunyao.InterviewQuestions.group_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 *
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 *
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么？
 *
 * 答：在t1中notify，t1并没有调用wait方法，没有释放锁，
 *     因此，t1会继续执行，直到结束；所以t2会等到t1结束后，才能拿到锁，得以执行
 *
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 *
 * 使用Latch（门闩）替代wait  notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count的值为0时，当前线程继续执行
 * 当不涉及同步，只涉及线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这时应该考虑countdownlatch/cyclicbarrier/semaphore
 *
 */
public class T05_CountDownLatch {

    private static CountDownLatch latch = new CountDownLatch(1);

    private static List<Object> list = new ArrayList<>(10);

    public void add(Object obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch t05_countDownLatch = new T05_CountDownLatch();
        new Thread(() -> {
            System.out.println("t1开始");
            for (int i = 0; i < 10; i++) {
                t05_countDownLatch.add(new Object());
                if(i == 4){
                    latch.countDown();
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();

        new Thread(() -> {
            System.out.println("t2开始");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t05_countDownLatch.size());
            System.out.println("t2结束");
        }).start();
    }

}
