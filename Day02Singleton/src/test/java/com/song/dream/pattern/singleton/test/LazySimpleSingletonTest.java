package com.song.dream.pattern.singleton.test;

import com.song.dream.pattern.singleton.test.ExectorThread;

/**
 * java.com.song.dream.pattern.singleton.test
 *
 * @author by Song
 * @date 2019/4/5 07:44
 */
public class LazySimpleSingletonTest {


    public static void main(String[] args) {
        Thread thread1 = new Thread(new ExectorThread());
        Thread thread2 = new Thread(new ExectorThread());
        thread1.start();
        thread2.start();
    }
}
