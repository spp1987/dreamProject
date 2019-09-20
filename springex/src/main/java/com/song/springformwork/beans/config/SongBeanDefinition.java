package com.song.springformwork.beans.config;

import lombok.Data;

/**
 * com.song.springformwork.beans.config
 *
 * @author by Song
 * @date 2019/6/24 15:00
 */
@Data
public class SongBeanDefinition {

    private String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;
}
