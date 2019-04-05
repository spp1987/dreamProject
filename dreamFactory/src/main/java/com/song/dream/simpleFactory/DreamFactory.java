package com.song.dream.simpleFactory;

import com.song.dream.ActorDream;
import com.song.dream.DreamService;
import com.song.dream.SwanDream;

/**
 * com.song.dream
 *
 * @author by Song
 * @date 2019/4/4 15:28
 */
public class DreamFactory {

    //创建逻辑放在简单工厂中，调用者不需要关心里面具体实践
    //创建逻辑不能太多，可维护性会变差
    //新增产品需要在之前逻辑添加，违背开闭规则

    public DreamService show(String showName){
        if("swan".equals(showName)){
            return new SwanDream();
        }else if("actor".equals(showName)){
            return new ActorDream();
        }else {
            return null;
        }
    }

    public DreamService show(Class clazz){
        try {
            if (null != clazz) {
                return (DreamService) clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
