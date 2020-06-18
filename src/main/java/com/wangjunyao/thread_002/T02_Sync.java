package com.wangjunyao.thread_002;

public class T02_Sync {

    private static int count = 100;

    public static void method1(){//�κ��߳�Ҫִ��������룬���������õ�T02_Sync class��������
        synchronized (T01_Sync.class){
            while (count > 0) {
                count--;
                System.out.println(Thread.currentThread().getName() + " amount = " + count);
            }
        }
    }

    public synchronized static void method2(){//�����ͬ��synchronized(T01_Sync.class)
        while (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + " amount = " + count);
        }
    }

    public static void mm() {
        synchronized(T02_Sync.class) { //����һ������дsynchronized(this)�Ƿ���ԣ�
                                      // ���벻ͨ����static�����в���ʹ��this�ؼ���
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
