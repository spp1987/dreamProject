package com.song.springformwork.beans;

/**
 * com.song.springformwork.beans
 *
 * @author by Song
 * @date 2019/6/26 17:59
 */
public class  SongBeanWrapper {

    public Object getWrappedInstance(){
        return this.wrappedInstance;
    }


    public Class<?> getWrappedClass(){
        return this.wrappedInstance.getClass();
    }

    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public SongBeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
    }


}
