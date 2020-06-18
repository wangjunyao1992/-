package com.wangjunyao.thread_010_moreAboutSync;

import java.util.concurrent.TimeUnit;

/**
 * ����ĳ������o�����o�����Է����ı䣬��Ӱ������ʹ�ã�
 * �������o�������һ�����������Ķ������ı䣬
 * Ӧ�ñ��⽫������������ñ������Ķ���  ���Կ���final
 */
public class SyncSameObject {

    private static /*final*/ Object o = new Object();

    public void method(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        SyncSameObject syncSameObject = new SyncSameObject();
        new Thread(syncSameObject::method, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        o = new Object();
        new Thread(syncSameObject::method, "thread2").start();
    }

}
