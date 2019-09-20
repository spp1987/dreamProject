package com.song.springformwork.context;

import com.song.springformwork.beans.SongBeanFactory;
import com.song.springformwork.beans.SongBeanWrapper;
import com.song.springformwork.beans.config.SongBeanDefinition;
import com.song.springformwork.beans.support.SongBeanDefinitionReader;
import com.song.springformwork.beans.support.SongDefaultListableBeanFactory;
import com.song.springv1.annotation.SongAutowired;
import com.song.springv1.annotation.SongController;
import com.song.springv1.annotation.SongService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * com.song.springformwork.context
 *
 * @author by Song
 * @date 2019/6/24 14:22
 */
public class SongApplicationContext extends SongDefaultListableBeanFactory implements SongBeanFactory {

    private String [] configLocation;
    private SongBeanDefinitionReader reader;
    //单例的IOC容器缓存
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<String, Object>();
    //通用的IOC容器
    private Map<String,SongBeanWrapper> factoryBeanInstanceChache = new ConcurrentHashMap<String, SongBeanWrapper>();

    public Object getBean(String beanName) {
        //1.初始化
        //doCreateBean
        SongBeanDefinition songBeanDefinition = this.beanDefinitionMap.get(beanName);
        SongBeanWrapper songBeanWrapper = instantiateBean(beanName,songBeanDefinition);
        //2.拿到beanWrapper之后，把BeanWrapper保存到IO容器之中
//        if(this.factoryBeanInstanceChache.containsKey(beanName)) {
//            throw new Exception("The "+beanName +"is exists!!!");
//        }
        this.factoryBeanInstanceChache.put(beanName,songBeanWrapper);
        //3.注入
        populateBean(beanName,new SongBeanDefinition(),songBeanWrapper);
        return this.factoryBeanInstanceChache.get(beanName).getWrappedInstance();
    }

    private SongBeanWrapper instantiateBean(String beanName, SongBeanDefinition songBeanDefinition) {
        //1.拿到要实例化对象的类名
        String className = songBeanDefinition.getBeanClassName();
        //2.反射实例化,得到一个对象
        Object instance = null;
        try{
            //假设默认单例模式
            if(this.singletonObjects.containsKey(className)){
                instance = this.singletonObjects.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.singletonObjects.put(className,instance);
                this.singletonObjects.put(songBeanDefinition.getFactoryBeanName(),instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        //3.把这个对象封装到BeanWrapper中
        SongBeanWrapper beanWrapper = new SongBeanWrapper(instance);


        //4.把BeanWrapper存在IOC容器里面



        return null;
    }

    private void populateBean(String beanName, SongBeanDefinition songBeanDefinition, SongBeanWrapper songBeanWrapper) {
        Object instance = songBeanWrapper.getWrappedInstance();

        Class<?> clazz = songBeanWrapper.getWrappedClass();
        //判断只有加了注解的类，才执行依赖注入
        if(!clazz.isAnnotationPresent(SongController.class) || clazz.isAnnotationPresent(SongService.class)){
            return;
        }
        //获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :
                fields) {
            if (!field.isAnnotationPresent(SongAutowired.class)) continue;
            SongAutowired autowired = field.getAnnotation(SongAutowired.class);
            String autowiredBeanname = autowired.value().trim();
            if("".equals(autowiredBeanname)){
                autowiredBeanname = field.getType().getName();
            }
            //强制访问
            field.setAccessible(true);
            try {
                field.set(instance,this.factoryBeanInstanceChache.get(autowiredBeanname).getWrappedClass());
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }


    public SongApplicationContext(String ... configLocation){
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void refresh() {
        //1.定位，定位配置文件
        reader = new SongBeanDefinitionReader(this.configLocation);

        //2.加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<SongBeanDefinition> beanDefinitions = new SongBeanDefinitionReader().loadBeanDefinitons();

        //3.注册，把配置信息放到容器里面(伪ioc容器) -
        doRegisterBeanDefinition(beanDefinitions);
        //4.把不是延迟加载的类，提前初始化
        doAutowried();
    }

    //只处理非延迟加载的情况
    private void doAutowried() {
        for (Map.Entry<String,SongBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                getBean(beanName);
            }
        }
    }

    private void doRegisterBeanDefinition(List<SongBeanDefinition> beanDefinitions) {
        for (SongBeanDefinition beanDefinition: beanDefinitions) {
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }
}
