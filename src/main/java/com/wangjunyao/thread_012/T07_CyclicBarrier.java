package com.wangjunyao.thread_012;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏。可循环利用的屏障
 *
 * 它的作用是会让所有线程都等待完成后才会继续下一步行动
 *
 */
public class T07_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人，发车"));
        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });*/
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("开始");
                    barrier.await();
                    System.out.println("结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
