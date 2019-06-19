package com.song.springv1.annotation;

import java.lang.annotation.*;

/**
 * com.song.springv1.annotation
 *
 * @author by Song
 * @date 2019/6/17 14:21
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SongAutowired {

    String value() default "";
}
