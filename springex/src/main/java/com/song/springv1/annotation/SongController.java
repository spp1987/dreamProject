package com.song.springv1.annotation;

import java.lang.annotation.*;

/**
 * com.song.springv1.annotation
 * 控制器注解
 * @author by Song
 * @date 2019/6/15 21:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SongController {
}
