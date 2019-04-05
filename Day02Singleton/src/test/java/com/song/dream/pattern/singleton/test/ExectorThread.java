package com.song.dream.pattern.singleton.test;

import com.song.dream.pattern.singleton.lazy.LazySimpleSingleton;

/**
 * java.com.song.dream.pattern.singleton.test
 *
 * @author by Song
 * @date 2019/4/5 07:45
 */
public class ExectorThread implements Runnable {


    public void run() {
        LazySimpleSingleton lazySimpleSingleton = LazySimpleSingleton.getInstance();
        System.err.println(Thread.currentThread().getName()+":"+lazySimpleSingleton.toString());
    }
}
