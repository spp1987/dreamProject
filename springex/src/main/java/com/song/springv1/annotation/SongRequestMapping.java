package com.song.springv1.annotation;

import java.lang.annotation.*;

/**
 * com.song.springv1.annotation
 *
 * @author by Song
 * @date 2019/6/17 14:27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SongRequestMapping {

    String name() default "";


    String value() default "";
}
