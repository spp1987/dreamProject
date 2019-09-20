package com.song.springformwork.beans.support;

import com.song.springformwork.beans.config.SongBeanDefinition;
import com.song.springformwork.context.support.SongAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * com.song.springformwork.beans.support
 *
 * @author by Song
 * @date 2019/6/24 14:55
 */
public class SongDefaultListableBeanFactory extends SongAbstractApplicationContext {

    //存储注册信息的BeanDefinition
    protected final Map<String, SongBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,SongBeanDefinition>();
}
