package com.wangjunyao.InterviewQuestions.group_003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用ArrayList，开启两个线程，一个线程往这个集合中添加数据，
 * 另一个线程中取这个集合的size值，为什么线程中取到的值一直取不到最新的值?
 */
public class T02_ListConcurrentIssuesSize {

    private List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        T02_ListConcurrentIssuesSize t03_list = new T02_ListConcurrentIssuesSize();
        new Thread(() -> {
            while (t03_list.list.size() == 0){
                /*
                 * 这里不能使用System.out.println方法来进行测试，因为该方法是线程安全的，
                 * 有同步标识，这样会导致list数据重新从主内存中加载一份
                 */
                //System.out.println(t03_list.list.size());
            }
            System.out.println("thread1 end =======");
        }, "thread1").start();
        TimeUnit.SECONDS.sleep(5);
        t03_list.list.add("123");
        System.out.println(t03_list.list.size());
    }

}
