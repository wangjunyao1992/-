package com.wangjunyao.thread_012;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser与CountDownLatch非常相似，允许我们协调线程的执行。
 * Phaser是在线程动态数需要执行之前等待的屏障。在CountDownLatch中，该数字无法动态配置，需要在创建实例时提供。
 *
 * Phaser是我们能够建立在逻辑线程需要才去执行下一步的障碍等。
 *
 * 我们可以协调多个执行阶段，为每个程序阶段重用Phaser实例。每个阶段可以有不同数量的线程
 * 等待前进到另一个阶段。
 *
 * 要参与协调，线程需要使用Phaser实例register()方法。请注意：这只会增加注册方的数量，我们无法
 * 检查当前线程是否已注册，我们必须将实现子类化支持此操作。
 *
 * 线程通过arriAwaitAdvance()来阻止它到达屏障，这是一个阻塞方法。当数量到达等于注册的数量时，
 * 程序的执行将继续，并且数量将增加。我们可以通过调用getPhase()方法获取当前数量。
 *
 * 当线程完成其工作时，我们应该调用arrivalAndDeregister()方法来表示在此特定阶段不在考虑当前线程。
 *
 */
public class T08_Phaser1 {

    private static Random random = new Random();

    private static MarriagePhaser marriagePhaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        marriagePhaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(() -> {
                Person p = new Person("person " + nameIndex);
                p.arrive();
                marriagePhaser.arriveAndAwaitAdvance();

                p.eat();
                marriagePhaser.arriveAndAwaitAdvance();

                p.leave();
                marriagePhaser.arriveAndAwaitAdvance();
            }).start();
        }
    }

    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人都到齐了！");
                    return false;
                case 1:
                    System.out.println("所有人吃完了！");
                    return false;
                case 2:
                    System.out.println("所有人离开了！");
                    System.out.println("婚礼结束！");
                    return true;
                default:
                    return true;

            }
        }
    }

    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
        }

        public void leave() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
        }

    }

}
