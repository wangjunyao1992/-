package com.wangjunyao.thread_006;

import java.util.concurrent.TimeUnit;

/**
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ�����������
 * �ٴ������ʱ����Ȼ��õ��ö������.Ҳ����˵synchronized��õ����ǿ������
 * �����Ǽ̳����п��ܷ��������Σ�������ø����ͬ������
 */
public class T02 {

    public synchronized void superMethod(){
        System.out.println("superMethod start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("superMethod end");
    }

    public static void main(String[] args) {
        TTT02 ttt02 = new TTT02();
        ttt02.childMethod();
    }

}

class TTT02 extends T02{
    public synchronized void childMethod(){
        System.out.println("childMethod start");
        super.superMethod();
        System.out.println("childMethod end");
    }
}
