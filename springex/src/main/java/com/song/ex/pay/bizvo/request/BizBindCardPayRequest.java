package com.song.ex.pay.bizvo.request;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 *
 * @author by Song
 * @date 2019/9/27 11:18
 */
@Data
public class BizBindCardPayRequest {


    //交易银行卡号 - 搜索对应人员，匹配对应特定信息
    private String bankCardNo;
    //银行code
    private String bankCode;
    //交易金额-元
    private String transactAmount;
    //业务还款订单交易号
    private String bizRepaymentOrderNo;

}
