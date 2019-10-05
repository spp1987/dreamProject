package com.song.ex.pay.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.song.ex.pay.common
 *
 * @author by Song
 * @date 2019/9/27 10:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException {

    private String respCode;

    private String respMessage;

    public BizException(ErrorInterface errorInfo) {
        this.respCode = errorInfo.getRespCode();
        this.respMessage = errorInfo.getRespMessage();
    }

    public BizException(ErrorInterface errorInfo,String errorMsg) {
        this.respCode = errorInfo.getRespCode();
        this.respMessage = errorInfo.getRespMessage()+errorMsg;
    }


}
