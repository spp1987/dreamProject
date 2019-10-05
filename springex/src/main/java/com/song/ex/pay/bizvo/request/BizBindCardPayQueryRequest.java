package com.song.ex.pay.bizvo.request;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 *
 * @author by Song
 * @date 2019/9/30 11:32
 */
@Data
public class BizBindCardPayQueryRequest {

    //业务还款订单交易号
    private String bizRepaymentOrderNo;
}
