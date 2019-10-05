package com.song.ex.pay.controller.hanlder;

import com.song.ex.pay.bizvo.BizBaseRequest;
import com.song.ex.pay.bizvo.BizBaseResponse;
import com.song.ex.pay.bizvo.result.PayBaseResult;

/**
 * com.song.ex.pay.controller.hanlder
 *
 * @author by Song
 * @date 2019/9/30 14:54
 */
public abstract class BaoFooAbstractHandler extends PayAbstractHandler{

    public BizBaseResponse doProcess(BizBaseRequest bizBaseRequest){
        BizBaseResponse bizBaseResponse = new BizBaseResponse();
        try {
            //1.根据产品code确认支付渠道
            //getPayRute
            //2.参数校验
            checkParams(bizBaseRequest);
            PayBaseResult payBaseResult = doRequestThreeInterface(bizBaseRequest,"");
            //参数转换及判断
            convertRetMessageByBaoFoo(payBaseResult,bizBaseResponse);
        }catch (Exception e){
            return null;
        }
        return bizBaseResponse;
    }


    void convertRetMessageByBaoFoo(PayBaseResult payBaseResult, BizBaseResponse bizBaseResponse) {
        if(payBaseResult.getStatus().equals("SUCCESS")){
            bizBaseResponse.setApiSuccessCode();
        }else if(payBaseResult.getStatus().equals("FAIL")){
            bizBaseResponse.setApiFailureCode();
        }
    }
}
