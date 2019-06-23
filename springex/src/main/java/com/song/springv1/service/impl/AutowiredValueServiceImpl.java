package com.song.springv1.service.impl;

import com.song.springv1.annotation.SongAutowired;
import com.song.springv1.annotation.SongService;
import com.song.springv1.service.AutowiredValueService;

/**
 * com.song.springv1.service.impl
 *
 * @author by Song
 * @date 2019/6/23 13:55
 */
@SongService("autowiredValueService")
public class AutowiredValueServiceImpl implements AutowiredValueService {

    public String getName(String name) {
        return "AutowiredValue : " + name;
    }
}
