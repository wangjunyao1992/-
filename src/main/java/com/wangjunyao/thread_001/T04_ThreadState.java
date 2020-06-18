package com.wangjunyao.thread_001;

public class T04_ThreadState {

    public static class MyThread extends Thread{
        @Override
        public synchronized void run() {
            System.out.println("1: " + this.getState());
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    //this.wait(1000);
                    System.out.println("2: " + this.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("main : " + myThread.getState());
        myThread.start();
        System.out.println("main : " + myThread.getState());
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main : " + myThread.getState());
        }
    }

}
