package com.song.springformwork.context.support;

/**
 * com.song.springformwork.context
 * IOC容器实现的顶层设计
 * @author by Song
 * @date 2019/6/24 14:25
 */
public abstract class SongAbstractApplicationContext {

    //受保护，只提供给子类重写
    protected void refresh(){}

}
