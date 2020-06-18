package com.wangjunyao.thread_005;

import java.util.concurrent.TimeUnit;

/**
 * �����⣺ģ�������˻�
 * ��ҵ��д��������
 * ��ҵ�������������
 * �����в��У�
 *
 * ��ҵ�������Ƿ��������
 */
public class T01 {

    private String name;

    private long balance;

    public T01(String name, long balance){
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(long balance){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            this.balance = balance;
        }
        System.out.println("��setBalance��name = " + this.name + ", balance = " + this.balance);
    }

    public long getBalance(){
        synchronized (this){
            return this.balance;
        }
    }

    public static void main(String[] args) {
        T01 t01 = new T01("��С��", 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                t01.setBalance(100000);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("��getBalance��" + t01.getBalance());
            }
        }).start();
    }


}
