package com.wangjunyao.thread_010_moreAboutSync;

/**
 *  ��Ҫ���ַ���������Ϊ��������
 *  ������������У�m1��m2��ʵ��������ͬһ������
 *  ����������ᷢ���ȽϹ�������󣬱������õ���һ����⣬�ڸ�����д����������ַ�����Hello����
 *  �����������Դ�룬���������Լ��Ĵ�����Ҳ������"Hello",��ʱ����п��ܷ����ǳ����������������
 *  ��Ϊ��ĳ�������õ�����ⲻ�����ʹ����ͬһ����
 */
public class DoNotLockString {

    String s1 = "Hello";
    String s2 = "Hello";

    void m1() {
        synchronized(s1) {

        }
    }

    void m2() {
        synchronized(s2) {

        }
    }

}
