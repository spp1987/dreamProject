package com.song.springv1.service.impl;

import com.song.springv1.annotation.SongService;
import com.song.springv1.service.DemoService;

/**
 * com.song.springv1.service.impl
 *
 * @author by Song
 * @date 2019/6/17 14:13
 */
@SongService
public class DemoServiceImpl implements DemoService {

    public String get(String name) {
        return "Service:"+name;
    }
}
