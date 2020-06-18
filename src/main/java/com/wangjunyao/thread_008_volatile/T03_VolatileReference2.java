package com.wangjunyao.thread_008_volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile 修饰引用类型（包括数组），只能保证引用本身的可见性，
 * 不能保证内部属性的可见性
 *
 * 下面代码理论上不应该有reader输出到控制台，但是多长运行代码，发现，
 * 有reader输出，说明，reader线程对writer线程中对data的设置是不可见的。
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
