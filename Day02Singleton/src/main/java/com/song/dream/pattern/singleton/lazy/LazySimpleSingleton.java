package com.song.dream.pattern.singleton.lazy;

/**
 * com.song.dream.pattern.singleton.lazy
 * 懒汉式单例
 * @author by Song
 * @date 2019/4/5 07:34
 */
public class LazySimpleSingleton {

    private LazySimpleSingleton(){}

    private static LazySimpleSingleton lazySimpleSingleton = null;

    public synchronized static LazySimpleSingleton getInstance(){
        if(null!=lazySimpleSingleton)return lazySimpleSingleton;
        lazySimpleSingleton = new LazySimpleSingleton();
        return lazySimpleSingleton;
    }

    public static void main(String[] args) {
        System.err.println(1);
    }
}
