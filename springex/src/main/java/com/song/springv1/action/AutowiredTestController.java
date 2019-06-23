package com.song.springv1.action;

import com.song.springv1.annotation.SongAutowired;
import com.song.springv1.annotation.SongController;
import com.song.springv1.annotation.SongRequestMapping;
import com.song.springv1.annotation.SongRequestParam;
import com.song.springv1.service.AutowiredValueService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.song.springv1.action
 *
 * @author by Song
 * @date 2019/6/23 13:51
 */
@SongController
@SongRequestMapping(value = "/testAutowired")
public class AutowiredTestController {

    @SongAutowired("autowiredValueService")
    private AutowiredValueService autowiredValueService;

    @SongRequestMapping("/testPrint")
    public void testPrint(HttpServletRequest request, HttpServletResponse response,
                          @SongRequestParam("name") String name,
                            String sex){
        try{
            String result = autowiredValueService.getName(name);
            response.getWriter().write(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
