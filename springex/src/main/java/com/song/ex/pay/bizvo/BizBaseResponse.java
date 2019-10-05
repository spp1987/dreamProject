package com.song.ex.pay.bizvo;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo
 *
 * @author by Song
 * @date 2019/9/29 11:15
 */
@Data
public class BizBaseResponse<T> {

    private String respCode;

    private String respMessage;

    private T obj;

    /**
     * 1.处理中
     * 2.失败
     * 3.成功
     */
    private String status;

    /**
     * 接口调用成功，结果失败
     */
    public void setApiFailureCode(){
        setRespCode("000000");
        setRespMessage("结果失败");
        setStatus("2");
    }

    /**
     * 接口调用成功，结果成功
     */
    public void setApiSuccessCode(){
        setRespCode("000000");
        setRespMessage("结果成功");
        setStatus("3");
    }
}
