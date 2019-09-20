package com.song.springformwork.beans;

/**
 * com.song.springformwork.beans
 * 单例工厂-顶层设计
 * @author by Song
 * @date 2019/6/24 14:17
 */
public interface SongBeanFactory {

    /**
     * 单例模式下 - 全局访问点
     * 根据beanName从ioc容器中获得一个实例Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
