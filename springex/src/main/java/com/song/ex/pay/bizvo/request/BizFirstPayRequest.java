package com.song.ex.pay.bizvo.request;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 * 首次支付
 * @author by Song
 * @date 2019/9/30 16:57
 */
@Data
public class BizFirstPayRequest {

    //银卡卡号
    private String bankCardNo;
    //银行预留手机号
    private String bankMobile;
    //身份证号码
    private String idCardNo;
    //客户姓名
    private String userName;
    //业务请求号
    private String bizRequestNo;
    //还款金额-元
    private String amount;

}
