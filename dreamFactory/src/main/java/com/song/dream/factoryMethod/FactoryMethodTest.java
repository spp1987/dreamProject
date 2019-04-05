package com.song.dream.factoryMethod;

import com.song.dream.DreamService;

/**
 * com.song.dream.factoryMethod
 *
 * @author by Song
 * @date 2019/4/4 19:48
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        DreamFactory dreamFactory = new SwanDreamFactory();
        DreamService dreamService = dreamFactory.show();
        dreamService.show();
        //工厂模板方法 产品链扩成时候，不需要修改原有产品类，直接新增产品流水线就可以
        //场景：创建对象需要大量重复得代码;
        //客户端(应用层)不依赖于产品实例如何被创建、实现等细节.
        //一个类通过其子类来指定创建哪个对象


        //缺点：增加得产品越多，类就会增多，增加代码结构的复杂度
        //增加系统得抽象性和理解难度
    }
}
