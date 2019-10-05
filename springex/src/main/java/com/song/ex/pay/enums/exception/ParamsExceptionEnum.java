package com.song.ex.pay.enums.exception;

import com.song.ex.pay.common.ErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * com.song.ex.pay.enums.exception
 *
 * @author by Song
 * @date 2019/9/27 10:52
 */
@Getter
@AllArgsConstructor
public enum ParamsExceptionEnum implements ErrorInterface {

    PARAM_IS_BLANK("P00001","参数不能为空"),

    ;

    private String respCode;

    private String respMessage;



}
