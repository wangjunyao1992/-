package com.wangjunyao.thread_008_volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile �����������ͣ��������飩��ֻ�ܱ�֤���ñ���Ŀɼ��ԣ�
 * ���ܱ�֤�ڲ����ԵĿɼ���
 *
 * ������������ϲ�Ӧ����reader���������̨�����Ƕ೤���д��룬���֣�
 * ��reader�����˵����reader�̶߳�writer�߳��ж�data�������ǲ��ɼ��ġ�
 */
public class T03_VolatileReference2 {

    private static class Data{
        int a, b;
        public Data(int a, int b){
            this.a = a;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.b = b;
            System.out.println("Data: " + "a = " + a + ", b = " + b);
        }
    }

    volatile static Data data;

    public static void main(String[] args) {
        Thread writer = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                data = new Data(i, i);
            }
        });

        Thread reader = new Thread(()->{
            while (data == null) {}
            int x = data.a;
            int y = data.b;
            if(x != y) {
                System.out.printf("reader: " + "a = %s, b=%s%n", x, y);
            }
        });

        reader.start();
        writer.start();

        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }

}
