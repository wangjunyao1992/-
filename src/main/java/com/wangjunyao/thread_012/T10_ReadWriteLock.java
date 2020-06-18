package com.wangjunyao.thread_012;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��д��
 * ��д������ͬһʱ�̱�������̷߳��ʣ�������д�̷߳���ʱ�����еĶ��̺߳�
 * ������д�̶߳��ᱻ������
 */
public class T10_ReadWriteLock {

    private static Lock lock = new ReentrantLock();

    private static int value;

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
            //ģ���ȡ����
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
            //ģ��д����
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //Runnable readRunnable = () -> read(lock);
        Runnable readRunnable = () -> read(readLock);

        //Runnable writeRunnable = () -> write(lock, new Random().nextInt());
        Runnable writeRunnable = () -> write(writeLock, new Random().nextInt());

        for(int i=0; i<18; i++) new Thread(readRunnable).start();
        for(int i=0; i<2; i++) new Thread(writeRunnable).start();
        for(int i=0; i<18; i++) new Thread(readRunnable).start();
    }

}
