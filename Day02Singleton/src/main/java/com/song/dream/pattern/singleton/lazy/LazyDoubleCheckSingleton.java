package com.song.dream.pattern.singleton.lazy;

/**
 * com.song.dream.pattern.singleton.lazy
 *
 * @author by Song
 * @date 2019/4/5 17:32
 */
public class LazyDoubleCheckSingleton {

    private LazyDoubleCheckSingleton(){}

    private static LazyDoubleCheckSingleton lazyDoubleCheckSigleton = null;

    //适中得方案 synchronized 放在方法上会锁类吗？TODO
    public static LazyDoubleCheckSingleton getInstance(){
        if(null==lazyDoubleCheckSigleton) {
            synchronized (LazySimpleSingleton.class) {
                if (null == lazyDoubleCheckSigleton) {
                    lazyDoubleCheckSigleton = new LazyDoubleCheckSingleton();
                    //指令重排序 - cpu执行时候会转换JVM指令执行
                    //1.分配内存给这个对象
                    //2.初始化对象
                    //3.将初始化好的对象和内存地址建立关联,赋值
                    //4.用户初次访问
                }
            }
        }
        return lazyDoubleCheckSigleton;
    }
}
