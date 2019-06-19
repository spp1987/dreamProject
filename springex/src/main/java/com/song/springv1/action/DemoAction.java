package com.song.springv1.action;

import com.song.springv1.annotation.SongAutowired;
import com.song.springv1.annotation.SongController;
import com.song.springv1.annotation.SongRequestMapping;
import com.song.springv1.annotation.SongRequestParam;
import com.song.springv1.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.song.springv1.action
 *
 * @author by Song
 * @date 2019/6/17 14:20
 */
@SongController
@SongRequestMapping("/demo")
public class DemoAction {

    @SongAutowired
    private DemoService demoService;

    @SongRequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response, @SongRequestParam String name){
        String result = "My name is " + name;
        try{
            response.getWriter().write(result);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
