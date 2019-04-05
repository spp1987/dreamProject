package com.song.dream.abstractFactory;

import com.song.dream.ActorDream;
import com.song.dream.DreamService;

/**
 * com.song.dream.abstractFactory
 *
 * @author by Song
 * @date 2019/4/4 22:10
 */
public class ActorDreamFactory implements DreamFactory {

    public DreamService createDream() {
        return new ActorDream();
    }

    public ITemperament createTemperament() {
        return null;
    }

    public IEdu createEdu() {
        return null;
    }
}
