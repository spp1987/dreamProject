package com.song.dream.pattern.singleton.test;

import com.song.dream.pattern.singleton.lazy.LazyDoubleCheckSingleton;

/**
 * java.com.song.dream.pattern.singleton.test
 *
 * @author by Song
 * @date 2019/4/5 07:45
 */
public class DoubleCheckExectorThread implements Runnable {


    public void run() {
        LazyDoubleCheckSingleton doubleCheckSigleton = LazyDoubleCheckSingleton.getInstance();
        System.err.println(Thread.currentThread().getName()+":"+doubleCheckSigleton.toString());
    }
}
