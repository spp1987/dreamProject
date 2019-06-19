package com.song.springv1.annotation;

import java.lang.annotation.*;

/**
 * com.song.springv1.annotation
 * 业务层service注解
 * @author by Song
 * @date 2019/6/15 20:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SongService {

    String value() default "";
}
