package com.song.dream.factoryMethod;

import com.song.dream.ActorDream;
import com.song.dream.DreamService;

/**
 * com.song.dream.factoryMethod
 * 工厂模式方法，可以
 * @author by Song
 * @date 2019/4/4 16:07
 */
public class ActorDreamFactory implements DreamFactory {

    public DreamService show() {
        return new ActorDream();
    }
}
