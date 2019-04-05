package com.song.dream.pattern.singleton.hungry;

/**
 * com.song.dream.pattern.singleton.hungry
 * 饿汉式单例
 * @author by Song
 * @date 2019/4/4 23:29
 */
public class HungryStaticSingleton {
    private static final HungryStaticSingleton hungrySingleton ;

    static {
        hungrySingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton(){}

    public static HungryStaticSingleton getInstance(){
        return hungrySingleton;
    }

    /**
     * 缺点：
     * 饿汉式单例.用不用都先初始化，浪费内存空间
     * 优化此种单例 - 懒汉式单例
     */
}
