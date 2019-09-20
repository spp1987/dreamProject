package com.song.springformwork.beans.support;

import com.song.springformwork.beans.config.SongBeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * com.song.springformwork.beans.support
 *
 * @author by Song
 * @date 2019/6/24 16:09
 */
public class SongBeanDefinitionReader {

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    private List<String> registyBeanClasses = new ArrayList<String>();

    public SongBeanDefinitionReader(String ...locations){
        InputStream is = null;
        if(locations.length<1){
            return;
        }
        try{
            is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
            config.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(null!=is){
                    is.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        try {
            //中文url展示encodeURI格式
            URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
            //URL url = this.getClass().
            //getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
            File classPath = new File(java.net.URLDecoder.decode(url.getFile(),"utf-8"));
            for (File file :
                    classPath.listFiles()) {

                if (file.isDirectory()) {
                    doScanner(scanPackage + "." + file.getName());
                } else {
                    if (!file.getName().endsWith(".class")) {
                        continue;
                    }
                    String className = (scanPackage + "." + file.getName().replace(".class", ""));
                    registyBeanClasses.add(className);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Properties getConfig(){
        return this.config;
    }

    //把配置文件中扫描到的所有配置信息转换未SongDefinition对象，便于以后ioc操作方便
    public List<SongBeanDefinition> loadBeanDefinitons(){
        List<SongBeanDefinition> beanDefinitionList = new ArrayList<SongBeanDefinition>();
        for (String className : registyBeanClasses) {
            SongBeanDefinition beanDefinition = doCreateBeanDefinition(className);
            if(null == beanDefinition)continue;
            beanDefinitionList.add(beanDefinition);
        }
        return beanDefinitionList;
    }

    //把每一个配信息解析成一个BeanDefinition
    private SongBeanDefinition doCreateBeanDefinition(String className){
        SongBeanDefinition beanDefinition = new SongBeanDefinition();
        try{
            Class<?> beanClass = Class.forName(className);
            //有可能是一个接口，用它的实现类作为beanClassName
            if(beanClass.isInterface()) return null;
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(beanClass.getSimpleName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return beanDefinition;
    }

    private String toLowerFirstCase(String beanName) {
        char [] chars = beanName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
