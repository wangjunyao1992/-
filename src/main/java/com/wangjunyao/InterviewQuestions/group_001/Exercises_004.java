package com.wangjunyao.InterviewQuestions.group_001;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用两个线程，一个输出字母，一个输出数据，交替输出1A2B3C4D...26Z
 */
public class Exercises_004 {

    private static AtomicInteger threadId = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

        new Thread(() -> {
            for (int i : ints) {
                while (1 != threadId.get()){}
                System.out.println(i);
                threadId.set(2);
            }
        }, "thread1").start();

        new Thread(() -> {
            for (char c : chars) {
                while (2 != threadId.get()){}
                System.out.println(c);
                threadId.set(1);
            }
        }, "thread2").start();
    }

}
