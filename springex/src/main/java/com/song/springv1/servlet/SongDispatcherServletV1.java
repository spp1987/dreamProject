package com.song.springv1.servlet;

import com.song.springv1.annotation.*;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * com.song.springv1.servlet
 * 增加注解别称的处理
 * @author by Song
 * @date 2019/6/19 16:57
 */
public class SongDispatcherServletV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            doDispatcher(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.getWriter().write("500 Exception!!!" + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 通过req获取url
     * url-method容器 -->url 找到method,然后调用
     * @param req
     * @param resp
     */
    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 not found Url:" + url);
            return;
        }
        Method method = this.handlerMapping.get(url);
        //通过反射拿到信息
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //为了方便这块写死，后期优化 = 这个定义死方法的参数及个数，无法自动扩展
        Map<String,String[]> paramMap = req.getParameterMap();
        Object[] paramValueArray = analysisMethod(method,req,resp);
        method.invoke(ioc.get(beanName),paramValueArray);

    }

    public Object[] analysisMethod(Method method,HttpServletRequest request,HttpServletResponse response){
        //形参列表
        Class<?> [] parameterTypes = method.getParameterTypes();
        //实参内容列表
        Object [] paramValue = new Object[parameterTypes.length];

        Map<String,String[]> paramMap = request.getParameterMap();
        //赋值过程
        for (int i = 0; i < parameterTypes.length; i++) {
            Class parameterType = parameterTypes[i];
            //不能使用 instanceof ,parameterType 不是实参 而是形参
            if(parameterType == HttpServletRequest.class){
                paramValue[i] = request;
            }else if(parameterType == HttpServletResponse.class){
                paramValue[i] = response;
            }else if(parameterType == String.class){
                //如果
                Annotation [][] methodParameterAnnotations = method.getParameterAnnotations();
                for (int j = 0; j < methodParameterAnnotations.length; j++) {
                    for (Annotation an : methodParameterAnnotations[j]) {
                        if(an instanceof SongRequestParam){
                            String paramName = ((SongRequestParam)an).value();
                            if(paramMap.containsKey(paramName)){
                                for (Map.Entry<String,String[]> param:paramMap.entrySet()){
                                    String value = Arrays.toString(param.getValue())
                                            .replaceAll("\\[|\\]","")
                                            .replaceAll("\\s","");
                                    paramValue[i] = value;
                                }
                            }
                        }

                    }
                }
            }
        }
        return paramValue;
    }

    //保存配置文件内容
    private Properties properties = new Properties();
    //保存扫描到的基类
    private List<String> classNames = new ArrayList();
    //传说中的ioc容器，
    private Map<String,Object> ioc = new HashMap<String,Object>();
    //save url - method - ref
    private Map<String,Method> handlerMapping = new HashMap<String, Method>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件信息-确定扫描基类
        doLoadConfig(config.getInitParameter("contextConfigLocation").replace("classpath:",""));
        //2.扫描相关的类
        doScanner(properties.getProperty("scanPackage"));
        //3.初始化扫描到相关的类，并且将他们放入到ioc容器之中
        doInstance();
        //4.完成依赖注入
        doAutowired();
        //5.初始化HandlerMapping - url-method关系
        initHandlerMapping();
        System.err.println("init完成!");
    }


    private void initHandlerMapping() {
        if(ioc.isEmpty()){return;}
        for(Map.Entry<String,Object> entry : ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(SongController.class)){continue;}
            String baseURL = "";
            if(clazz.isAnnotationPresent(SongRequestMapping.class)){
                SongRequestMapping requestMapping = clazz.getAnnotation(SongRequestMapping.class);
                baseURL = requestMapping.value();
            }
            //get public method
            for(Method method : clazz.getMethods()){
                if(!method.isAnnotationPresent(SongRequestMapping.class)){continue;}
                SongRequestMapping requestMapping = method.getAnnotation(SongRequestMapping.class);
                String url = ("/"+baseURL+"/"+requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.err.println("url:"+url+"|| method:"+method);
            }
        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String, Object> entry :
                ioc.entrySet()) {
            //Declared 所有的，特定字段，包括prinvate/protected/default
            //正常来说，普通的oop编程只能拿到public的属性
            Field [] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field :
                    fields) {
                if (!field.isAnnotationPresent(SongAutowired.class)) {
                    continue;
                }
                SongAutowired songAutowired = field.getAnnotation(SongAutowired.class);
                String beanName = songAutowired.value();
                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                //private 在accessible=true 才能被操作
                field.setAccessible(true);
                try{
                    //通过反射机制，动态给字段赋值 - 实例化
                    field.set(entry.getValue(),ioc.get(beanName));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        //1.判断classNames是否为空
        //2.遍历，判断该类是否被注解，是就加入到ioc容器计划中
        if(classNames.isEmpty()){return;}
        try {
            for (String className :
                    classNames) {
                Class<?> clazz = Class.forName(className);
                //这里面的类很多，什么样的类才具备实例化- 加了注解才能被实例化
                //1.一般遵循最小字母小写 - 驼峰命名
                String beanName = toLowerFirstCase(clazz.getSimpleName());
                if (clazz.isAnnotationPresent(SongController.class)) {
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);
                } else if (clazz.isAnnotationPresent(SongService.class)) {
                    Object instance = clazz.newInstance(); // 反射对象还是注解上具体某个类的对象
                    //2.自定义的beanName
                    SongService songService = clazz.getAnnotation(SongService.class);
                    if(!"".equals(songService.value())){
                        beanName = songService.value();
                        ioc.put(beanName,instance);
                    }
                    //3.根据类型自动赋值 -- 这里去找对象上级结构
                    for (Class<?> i: clazz.getInterfaces()) {
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The "+i.getName()+"is exception !!!");
                        }
                        ioc.put(i.getName(), instance);
                    }
                } else{
                    continue;
                }
            }
        }catch (InstantiationException e){
            e.printStackTrace(); //clazz.newInstance(); 显示抛出异常
        }catch (IllegalAccessException e){
            e.printStackTrace();//clazz.newInstance(); 显示抛出异常
        } catch (ClassNotFoundException e) {
            e.printStackTrace();//class.forName();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String toLowerFirstCase(String beanName) {
        char [] chars = beanName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


    private void doScanner(String scanPackage) {
        //scanPackage = com.song.springv1
        //转换为文件路径，实际上就是把.替换成/就oK .
        //classpath
        URL url = this.getClass().
                getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());
        for (File file :
                classPath.listFiles()) {
            if (file.isDirectory()) { //如果文件下面还是文件，循环下去
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }//不是文件也不是.class文件，跳到下一个循环
                String className = (scanPackage + "." + file.getName().replaceAll(".class", ""));
                classNames.add(className);
            }
        }
    }

    /**
     * 加载配置文件信息
     * @param contextConfigLocation
     */
    private void doLoadConfig(String contextConfigLocation) {
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            //从web.xml拿到配置文件地址，安装地址转换输入流信息，读到内存中
            properties.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null!=ins){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
