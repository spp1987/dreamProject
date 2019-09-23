package com.song.ex.threadEx;

import java.util.concurrent.TimeUnit;

/**
 * com.song.ex.threadEx
 * see http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/native/java/lang/Thread.c
 * @author by Song
 * @date 2019/9/21 08:14
 */
public class ThreadDataSafe0921 {
    static int i = 0;
    public void getNum()throws InterruptedException{
        Thread thread1 = new Thread(()->{
            for (int j = 0; j < 500; j++) {
                synchronized (this) {
                    i++;
                }
            }
        });

        Thread thread2 = new Thread(()->{
            for (int j = 0; j < 500; j++) {
                synchronized (ThreadDataSafe0921.class) {
                    i++;
                }
            }
        });
        thread1.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        System.err.println("所有线程执行完成后i："+i);
    }



    public static void main(String[] args) {
        //sleep 10秒钟 确保所有线程都执行完
        try {
            for (int j = 0; j < 100; j++) {
                new ThreadDataSafe0921().getNum();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
