package com.wangjunyao.thread_011_AtomicXXX;

import java.util.concurrent.atomic.LongAdder;

public class T03_SyncVsLongAdder {

    private static int count1 = 0;

    private static LongAdder count2 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[500];

        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new Thread(()-> {
                        for(int k=0; k<100000; k++) count2.increment();
                    });
        }

        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        //TimeUnit.SECONDS.sleep(10);

        System.out.println("LongAdder: " + count2.longValue() + " time " + (end-start));
        //-----------------------------------------------------------
        Object lock = new Object();

        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int k=0; k<100000; k++) {
                                synchronized (lock) {
                                    count1++;
                                }
                            }
                        }
                    });
        }

        start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        end = System.currentTimeMillis();


        System.out.println("Sync: " + count1 + " time " + (end-start));
    }

}
