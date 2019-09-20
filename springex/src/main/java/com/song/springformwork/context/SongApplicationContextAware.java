package com.song.springformwork.context;

/**
 * com.song.springformwork.context
 * 通过解耦方式获得IOC容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口
 * 将自动调用setApplicationContext()方法，从而将IOC容器注入到目标类中
 * @author by Song
 * @date 2019/6/24 15:42
 */
public interface SongApplicationContextAware {

    void setApplicationContext(SongApplicationContext applicationContext);
}
