package com.song.ex.pay.service.BaoFoo;

import com.song.ex.pay.bizvo.BizBaseRequest;
import com.song.ex.pay.bizvo.request.BizBindCardRequest;
import com.song.ex.pay.bizvo.response.BizBindCardResponse;
import com.song.ex.pay.bizvo.result.BindCardResult;
import com.song.ex.pay.bizvo.result.PayBaseResult;
import com.song.ex.pay.service.BindCardService;

/**
 * com.song.ex.pay.service.BaoFoo
 *
 * @author by Song
 * @date 2019/9/29 16:14
 */
public class BaoFooBindCardService implements BindCardService {

    @Override
    public PayBaseResult bindCard(BizBaseRequest bizBaseRequest) {
        BizBindCardRequest bizBindCardRequest = (BizBindCardRequest) bizBaseRequest;
        BizBindCardResponse bindCardResponse = new BizBindCardResponse();
        BindCardResult bindCardResult = new BindCardResult();//TODO 调用三方支付拿到返回结果
        return bindCardResult;
    }

    @Override
    public PayBaseResult confirmBindCard(BizBaseRequest bizBaseRequest) {
        return null;
    }
}
