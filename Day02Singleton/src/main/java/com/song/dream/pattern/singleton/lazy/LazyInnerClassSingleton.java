package com.song.dream.pattern.singleton.lazy;

/**
 * com.song.dream.pattern.singleton.lazy
 * 静态内部 单例
 * @author by Song
 * @date 2019/4/5 19:22
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){}

    //懒汉式单例
    //静态内部类逻辑需要等外部调用才可以去实例化操作
    //巧妙利用了内部类特性
    //jvm底层执行逻辑,完美地避免了线程安全问题
    public static final LazyInnerClassSingleton getInstance(){
        return LazyHolder.LAZY;
    }

    public static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }

}
