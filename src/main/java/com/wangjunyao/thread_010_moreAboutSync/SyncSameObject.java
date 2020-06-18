package com.wangjunyao.thread_010_moreAboutSync;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象o，如果o的属性发生改变，不影响锁的使用，
 * 但是如果o变成另外一个对象，则锁的对象发生改变，
 * 应该避免将锁定对象的引用变成另外的对象  可以考虑final
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
