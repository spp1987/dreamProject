package com.song.ex.pay.bizvo.request;

import com.song.ex.pay.bizvo.BizBaseRequest;
import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 *
 * @author by Song
 * @date 2019/9/27 10:57
 */
@Data
public class BizBindCardRequest extends BizBaseRequest {

    //银卡卡号
    private String bankCardNo;
    //银行预留手机号
    private String bankMobile;
    //身份证号码
    private String idCardNo;
    //客户姓名
    private String userName;
    /****绑卡四要素****/
    //其他参数来确认绑卡路由--如商户在交易中心配置的code -
    //绑卡路由需要跟支付路由一起
    private String bizRequestNo;

}
