package com.song.dream.pattern.singleton.register;

/**
 * com.song.dream.pattern.singleton.enums
 * 枚举单例
 * @author by Song
 * @date 2019/4/5 22:57
 */
public enum EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
