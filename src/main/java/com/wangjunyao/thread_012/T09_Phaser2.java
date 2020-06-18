package com.wangjunyao.thread_012;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T09_Phaser2 {

    private static Random random = new Random();

    private static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("person" + i)).start();
        }

        new Thread(new Person("����")).start();
        new Thread(new Person("����")).start();
    }

    static class Person implements Runnable{
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s �����ֳ���\n", name);

            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s ����!\n", name);

            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s �뿪��\n", name);

            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if(name.equals("����") || name.equals("����")) {
                milliSleep(random.nextInt(1000));
                System.out.printf("%s ������\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
                //phaser.register()
            }
        }

        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();
        }

    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("�����˶������ˣ�");
                    return false;
                case 1:
                    System.out.println("�����˳����ˣ�");
                    return false;
                case 2:
                    System.out.println("�������뿪�ˣ�");
                    System.out.println("���������");
                    return true;
                case 3:
                    System.out.println("����������������ﱧ����" + registeredParties);
                    return true;
                default:
                    return true;

            }
        }
    }

}
