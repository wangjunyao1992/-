package com.wangjunyao.thread_012;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser��CountDownLatch�ǳ����ƣ���������Э���̵߳�ִ�С�
 * Phaser�����̶߳�̬����Ҫִ��֮ǰ�ȴ������ϡ���CountDownLatch�У��������޷���̬���ã���Ҫ�ڴ���ʵ��ʱ�ṩ��
 *
 * Phaser�������ܹ��������߼��߳���Ҫ��ȥִ����һ�����ϰ��ȡ�
 *
 * ���ǿ���Э�����ִ�н׶Σ�Ϊÿ������׶�����Phaserʵ����ÿ���׶ο����в�ͬ�������߳�
 * �ȴ�ǰ������һ���׶Ρ�
 *
 * Ҫ����Э�����߳���Ҫʹ��Phaserʵ��register()��������ע�⣺��ֻ������ע�᷽�������������޷�
 * ��鵱ǰ�߳��Ƿ���ע�ᣬ���Ǳ��뽫ʵ�����໯֧�ִ˲�����
 *
 * �߳�ͨ��arriAwaitAdvance()����ֹ���������ϣ�����һ�������������������������ע�������ʱ��
 * �����ִ�н��������������������ӡ����ǿ���ͨ������getPhase()������ȡ��ǰ������
 *
 * ���߳�����乤��ʱ������Ӧ�õ���arrivalAndDeregister()��������ʾ�ڴ��ض��׶β��ڿ��ǵ�ǰ�̡߳�
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
                    System.out.println("�����˶������ˣ�");
                    return false;
                case 1:
                    System.out.println("�����˳����ˣ�");
                    return false;
                case 2:
                    System.out.println("�������뿪�ˣ�");
                    System.out.println("���������");
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
            System.out.printf("%s �����ֳ���\n", name);
        }

        public void eat() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s ����!\n", name);
        }

        public void leave() {
            milliSleep(random.nextInt(1000));
            System.out.printf("%s �뿪��\n", name);
        }

    }

}
