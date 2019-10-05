package com.song.ex.pay.controller.hanlder;

import com.song.ex.pay.bizvo.BizBaseRequest;
import com.song.ex.pay.bizvo.result.PayBaseResult;
import com.song.ex.pay.utils.ValidationUtils;

/**
 * com.song.ex.pay.service
 *
 * @author by Song
 * @date 2019/9/29 11:46
 */
public abstract class PayAbstractHandler {

    //抽出基础的步骤逻辑，各个通过细化业务都可以在自己里面添加步骤 或者是简化步骤。这个或者是空，相当于一个标杆

    //检验业务参数必填项
    //验签--doing checkSign()
    protected void checkParams(BizBaseRequest bizBaseRequest) {
        ValidationUtils.validate(bizBaseRequest);
    }

    /**
     * 请求三方接口
     * @param bizBaseRequest
     * @param payChannelCode
     */
    public abstract PayBaseResult doRequestThreeInterface(BizBaseRequest bizBaseRequest, String payChannelCode);


}
