package com.song.ex.pay.bizvo;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 *
 * @author by Song
 * @date 2019/9/27 11:43
 */
@Data
public class BizBaseRequest {

    //产品编码 - 确定支付路由
    private String productCode;


}
