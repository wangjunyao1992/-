package com.wangjunyao.InterviewQuestions.group_001;

/**
 * 用两个线程，一个输出字母，一个输出数据，交替输出1A2B3C4D...26Z
 */
public class Exercises_003 {
    enum ReadyToRun{
        T1, T2;
    }

    private static volatile ReadyToRun run = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        int[] nums = initNums();

        Thread thread1 = new Thread(() -> {
            for (int n : nums) {
                while (run != ReadyToRun.T1){}//自旋
                System.out.println(n);
                run = ReadyToRun.T2;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (char c : chars) {
                while (run != ReadyToRun.T2) {}//自旋
                System.out.println(c);
                run = ReadyToRun.T1;

            }
        });
        thread1.start();
        thread2.start();
    }

    private static int[] initNums(){
        int[] nums = new int[26];
        for (int i = 0; i < 26; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }


}
