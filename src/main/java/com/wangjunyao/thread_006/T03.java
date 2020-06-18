package com.wangjunyao.thread_006;

/**
 *  ���ڡ��ؼ���synchronized���ܱ��̳С���һ�����
 *
 * 1.����̳и���ʱ�����û����д�����е�ͬ������������ͬһ����
 *   �ڲ�ͬ�̲߳������ø÷���ʱ������ͬ��Ч����
 *
 * 2.����̳и��࣬������д�����е�ͬ����������û����ӹؼ���synchronized��
 *   ����ͬһ�����ڲ�ͬ�̲߳������ø÷���ʱ�����پ���ͬ��Ч����
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
 * �̳и��࣬û����д���෽����ֱ�ӵ���method��Ȼ��ͬ������
 */
class TT01 extends T03{

}

/**
 * �̳и��࣬����д���෽����û��ʹ��synchronized
 */
class TT02 extends T03{
    @Override
    public void method1() {
        count++;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
