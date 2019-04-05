package com.song.dream.abstractFactory;

import com.song.dream.DreamService;
import com.song.dream.SwanDream;

/**
 * com.song.dream.abstractFactory
 *
 * @author by Song
 * @date 2019/4/4 20:46
 * 可以把工厂模式父接口拆分多个产品接口，子产品需要哪些就实现哪些
 */
public class SwanDreamFactory implements DreamFactory{

    public DreamService createDream() {
        return new SwanDream();
    }

    public IEdu createEdu() {
        return null;
    }

    public ITemperament createTemperament() {
        return null;
    }
}
