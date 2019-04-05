package com.song.dream.abstractFactory;

import com.song.dream.DreamService;

/**
 * com.song.dream.abstractFactory
 *
 * @author by Song
 * @date 2019/4/4 20:25
 */
//要求所有子工厂都要实现这个工厂
//一个品牌得抽象
public interface DreamFactory extends EduFactory{

    DreamService createDream();

    ITemperament createTemperament();
}
