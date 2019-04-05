package com.song.dream.simpleFactory;

import com.song.dream.DreamService;
import com.song.dream.SwanDream;

/**
 * com.song.dream
 * 简单工厂测试类
 * @author by Song
 * @date 2019/4/4 15:33
 */
public class SimpleFactoryDreamTest {

    public static void main(String[] args) {
        DreamFactory dreamFactory = new DreamFactory();
        DreamService dreamService = dreamFactory.show(SwanDream.class);
        dreamService.show();
    }
}
