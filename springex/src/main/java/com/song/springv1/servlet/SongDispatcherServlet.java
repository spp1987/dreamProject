package com.song.springv1.servlet;

import com.song.springv1.annotation.SongAutowired;
import com.song.springv1.annotation.SongController;
import com.song.springv1.annotation.SongRequestMapping;
import com.song.springv1.annotation.SongService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * com.song.springv1.servlet
 * 模拟springmvc的servlet处理类 - DispatcherServlet
 * @author by Song
 * @date 2019/6/15 21:57
 */
public class SongDispatcherServlet extends HttpServlet {

    //保存配置文件内容
    private Properties contextConfig = new Properties();
    //保存所有扫描到的类名
    private List<String> classNames = new ArrayList<String>();
    //传说中的ioc容器，为此来揭开它神秘的面纱
    private Map<String,Object> ioc = new HashMap<String,Object>();
    //保存url 很Method的对应关系
    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //真正执行-
        try {
            doDispath(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Excetion! Detail :"+Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 通过req加载url - method
     * @param req
     * @param resp
     */
    private void doDispath(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 not found!!!");
            return;
        }
        Map<String,String[]> paramMap = req.getParameterMap();
        Method method = this.handlerMapping.get(url);
        //通过反射拿到信息
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //为了方便这块写死，后期需要优化

        method.invoke(ioc.get(beanName),new Object[]{req,resp,paramMap.get("name")[0]});
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件信息 - 确定扫描基类
        doLoadConfig(config.getInitParameter("contextConfigLocation").replace("classpath:",""));
        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        //3.初始化扫描到相关的类，并且将他们放入到IOC容器之中
        doInstance();
        //4.完成依赖注入
        doAutowired();
        //5.初始化HandlerMapping
        initHanlderMapping();

        System.err.println("init 完成！！！");
    }

    private void initHanlderMapping() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String, Object> entry :
                ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(SongController.class)){continue;}
            String baseURL= "";
            if(clazz.isAnnotationPresent(SongRequestMapping.class)){
                SongRequestMapping requestMapping = clazz.getAnnotation(SongRequestMapping.class);
                baseURL = requestMapping.value();
            }
            //默认获取所有public方法
            for (Method method :
                    clazz.getMethods()) {
                if(!method.isAnnotationPresent(SongRequestMapping.class)){
                    continue;
                }
                SongRequestMapping requestMapping = method.getAnnotation(SongRequestMapping.class);
                String url = ("/" + baseURL + "/" + requestMapping.value()).replaceAll("/+","/");

                handlerMapping.put(url,method);
                System.err.println("Mapped:"+url+","+method);
            }
        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String,Object> entry : ioc.entrySet()){
            //Declared 所有的，特定的 字段，包括private/protected/default
            //正常来说，普通的oop编程只能拿到public的属性
            Field [] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field :
                    fields) {
                if(!field.isAnnotationPresent(SongAutowired.class)){
                    continue;
                }
                SongAutowired songAutowired = field.getAnnotation(SongAutowired.class);
                String beanName = songAutowired.value().trim() ;
                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                //private 在accessible=true 才能被操作
                field.setAccessible(true);
                try{
                    //用反射机制，动态给字段赋值 -- 实例化
                    field.set(entry.getValue(),ioc.get(beanName));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        //初始化，为DI做准备
        if(classNames.isEmpty()){return;}
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                //什么样的类才需要初始化
                //加了注解的类，才能被初始化，如何判断？
                //1.默认类名首字母小写
                String beanName = toLowerFirstCase(clazz.getSimpleName());

                if(clazz.isAnnotationPresent(SongController.class)){
                    Object instance = clazz.newInstance();
                    //Spring默认类名首字母小写
                    ioc.put(beanName,instance);
                }else if(clazz.isAnnotationPresent(SongService.class)){
                    Object instance = clazz.newInstance();
                    //2.自定义的beanName
                    SongService service = clazz.getAnnotation(SongService.class);
                    if(!"".equals(service.getClass())){
                        beanName = service.value();
                    }
                    //3.根据类型自动赋值
                    for (Class<?> i : clazz.getInterfaces()) {
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The " + i.getName() + "is exists !!!");
                        }
                        ioc.put(i.getName(),instance);
                    }
                }else{
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //如果类名本身是小写就会有问题
    //这个方法本身就是大写转小写。只关注方法本身
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        //大小写字母的ASCII码相差32
        chars[0] += 32;
        return String.valueOf(chars);
    }


    private void doScanner(String scanPackage) {
        //scanPackage = com.song.springv1
        //转换为文件路径，实际上就是把.替换成/就OK
        //classpath
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(className);
            }
        }
    }

    /**
     * 加载配置文件信息
     */
    private void doLoadConfig(String contextConfigLocation) {
        //web.xml读取配置文件信息
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try{
            //并且将其读取出来的放到Properties
            //把配置文件内容读出来到内存中
            contextConfig.load(fis);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(null!=fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
