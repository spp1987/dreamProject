package com.song.dream.factoryMethod;

import com.song.dream.SwanDream;

/**
 * com.song.dream.factoryMethod
 *
 * @author by Song
 * @date 2019/4/4 16:06
 */
public class SwanDreamFactory implements DreamFactory {

    public SwanDream show() {
        return new SwanDream();
    }
}
