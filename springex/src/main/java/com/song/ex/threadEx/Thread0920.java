package com.song.ex.threadEx;

import java.util.concurrent.TimeUnit;

/**
 * com.song.ex.threadEx
 * 模拟各个线程竞争状态
 * @author by Song
 * @date 2019/9/20 17:42
 */
public class Thread0920 {

    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Time_waiting_thread").start();

        new Thread(()->{
            while (true){
                synchronized (Thread0920.class) {
                    try {
                        Thread0920.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Waiting_Thread").start();


        new Thread(new BlockedDemo(),"Block01_thread").start();
        new Thread(new BlockedDemo(),"Block02_thread").start();

    }


    static class BlockedDemo extends Thread{
        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
