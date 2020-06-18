package com.wangjunyao.InterviewQuestions.group_001;

/**
 * 用两个线程，一个输出字母，一个输出数据，交替输出1A2B3C4D...26Z
 */
public class Exercises_001 {

    private static Object object = new Object();

    private static boolean flag = true;

    private static char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    private static class NumThread extends Thread{
        @Override
        public void run() {
            synchronized (object){
                for (int i : ints) {
                    if(!flag){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                    flag = false;
                    object.notifyAll();
                }
            }
        }
    }

    private static class CharThread extends Thread{
        @Override
        public void run() {
            synchronized (object){
                for (char c : chars) {
                    if(flag){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(c);
                    flag = true;
                    object.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        NumThread numThread = new NumThread();
        CharThread charThread = new CharThread();
        numThread.start();
        charThread.start();
    }

}
