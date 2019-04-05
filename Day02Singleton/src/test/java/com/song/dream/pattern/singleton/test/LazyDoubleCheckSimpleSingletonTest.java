package com.song.dream.pattern.singleton.test;

/**
 * java.com.song.dream.pattern.singleton.test
 *
 * @author by Song
 * @date 2019/4/5 07:44
 */
public class LazyDoubleCheckSimpleSingletonTest {


    public static void main(String[] args) {
        Thread thread1 = new Thread(new DoubleCheckExectorThread());
        Thread thread2 = new Thread(new DoubleCheckExectorThread());
        thread1.start();
        thread2.start();
    }
}
