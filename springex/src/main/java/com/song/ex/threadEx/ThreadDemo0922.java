package com.song.ex.threadEx;

import java.util.concurrent.TimeUnit;

/**
 * com.song.ex.threadEx
 *
 * @author by Song
 * @date 2019/9/22 10:43
 */
public class ThreadDemo0922 {

    public static void main(String[] args) throws InterruptedException{
        //threadOuter();
//        for (int i = 0; i < 10; i++) {
//            threadWithin(i);
//        }

        System.err.println(main());
    }

    public static String main(){
        return "main重载方法";
    }


    public static void threadOuter()throws InterruptedException{
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                    //TimeUnit.SECONDS.sleep(1);
                    System.err.println("demo");
            }
        });
        thread.setName("threadOuter");
        thread.start();
        //TimeUnit.SECONDS.sleep(1);
        System.err.println("当前线程状态："+thread.getState());
        thread.interrupt();
        System.err.println(thread.isInterrupted());
    }

    public static void threadWithin(int methodNum)throws InterruptedException{
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };
        Thread thread = new Thread(()->{
            int num = threadLocal.get();
            while (!Thread.currentThread().isInterrupted()){
                System.err.println(methodNum+"threadWithin-线程运行");
                num++;
                if(num>3){
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.setName("threadWithin");
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.err.println(methodNum+":当前线程状态："+thread.getState());
        System.err.println(methodNum+":"+thread.isInterrupted());
    }
}
