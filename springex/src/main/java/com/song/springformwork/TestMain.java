package com.song.springformwork;

import com.song.springformwork.context.SongApplicationContext;

/**
 * com.song.springformwork
 *
 * @author by Song
 * @date 2019/6/28 14:27
 */
public class TestMain {

    public static void main(String[] args) {
        SongApplicationContext context = new SongApplicationContext("songSpringMvc.properties");
        try{
            Object object = context.getBean("myAction");
            System.err.println(object);
        }catch (Exception e){

        }
        System.err.println(context);
    }
}
