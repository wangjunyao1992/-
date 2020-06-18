package com.wangjunyao.thread_006;

/**
 *  关于“关键字synchronized不能被继承”的一点理解
 *
 * 1.子类继承父类时，如果没有重写父类中的同步方法，子类同一对象，
 *   在不同线程并发调用该方法时，具有同步效果。
 *
 * 2.子类继承父类，并且重写父类中的同步方法，但没有添加关键字synchronized，
 *   子类同一对象，在不同线程并发调用该方法时，不再具有同步效果。
 */
public class T03 {

    int count;

    public synchronized void method1(){
        count++;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        TT01 tt01 = new TT01();
        for (int i = 0; i < 100; i++) {
            new Thread(tt01::method1).start();
        }

        TT02 tt02 = new TT02();
        for (int i = 0; i < 100; i++) {
            new Thread(tt02::method1).start();
        }
    }
}

/**
 * 继承父类，没有重写父类方法，直接调用method任然是同步方法
 */
class TT01 extends T03{

}

/**
 * 继承父类，并重写父类方法，没有使用synchronized
 */
class TT02 extends T03{
    @Override
    public void method1() {
        count++;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
