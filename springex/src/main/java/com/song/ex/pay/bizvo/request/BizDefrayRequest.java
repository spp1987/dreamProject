package com.song.ex.pay.bizvo.request;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 *
 * @author by Song
 * @date 2019/9/30 10:34
 */
@Data
public class BizDefrayRequest {

    //代付业务请求单号
    private String bizDefrayRequestNo;
    //代付金额-元
    private String defrayAmount;
    //银行卡
    private String bankCardNo;
    //银行code
    private String bankCode;
    //账户名称
    private String userName;

}
