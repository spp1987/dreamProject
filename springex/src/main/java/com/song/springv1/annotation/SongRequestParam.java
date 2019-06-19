package com.song.springv1.annotation;

import java.lang.annotation.*;

/**
 * com.song.springv1.annotation
 *
 * @author by Song
 * @date 2019/6/17 14:29
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SongRequestParam {

    String value() default "";
}
