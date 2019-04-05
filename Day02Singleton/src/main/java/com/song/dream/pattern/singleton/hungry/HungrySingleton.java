package com.song.dream.pattern.singleton.hungry;

/**
 * com.song.dream.pattern.singleton.hungry
 * 饿汉式单例
 * @author by Song
 * @date 2019/4/4 23:29
 */
public class HungrySingleton {
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
