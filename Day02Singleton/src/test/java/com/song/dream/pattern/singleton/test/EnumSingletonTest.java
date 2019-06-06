package com.song.dream.pattern.singleton.test;

import com.song.dream.pattern.singleton.register.EnumSingleton;

/**
 * com.song.dream.pattern.singleton.test
 *
 * @author by Song
 * @date 2019/4/5 23:07
 */
public class EnumSingletonTest {

    public static void main(String[] args) {
        EnumSingleton enumSingleton = EnumSingleton.getInstance();
        enumSingleton.setData("ssddd");
        System.err.println(enumSingleton.getData());

    }
}
