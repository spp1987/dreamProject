package com.song.dream.factoryMethod;

import com.song.dream.DreamService;

/**
 * com.song.dream.factoryMethod
 * 注：新产品创建 放在每个类得对应流水线上，方便以后得扩展
 * 新增产品 只需要新增产品线，对之前的代码不影响，或者之前得产品不需要之前把对应产品工厂删除
 * @author by Song
 * @date 2019/4/4 16:05
 */
public interface DreamFactory {

    DreamService show();


    //把接口 -> 修改成抽象方法，把公共方法写到上层抽象类中
}
