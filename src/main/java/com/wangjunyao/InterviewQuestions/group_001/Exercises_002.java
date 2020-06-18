package com.wangjunyao.InterviewQuestions.group_001;

import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

/**
 * 用两个线程，一个输出字母，一个输出数据，交替输出1A2B3C4D...26Z
 */
public class Exercises_002 {

    private static Thread thread1 = null;

    private static Thread thread2 = null;

    public static void main(String[] args) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        int[] nums = initNums();
        /**
         * 数字多线程
         */
        thread1 = new Thread(() -> {
            for (int n : nums) {
                System.out.println(n);
                LockSupport.unpark(thread2);
                LockSupport.park();
            }
        });

        /**
         * 字符多线程
         */
        thread2 = new Thread(() -> {
            for (char c : chars) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(thread1);
            }
        });

        thread1.start();
        thread2.start();
    }

    public static int[] initNums(){
        int[] nums = new int[26];
        for (int i = 0; i < 26; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }
}
