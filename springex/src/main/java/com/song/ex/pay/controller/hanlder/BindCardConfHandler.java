package com.song.ex.pay.controller.hanlder;

import com.song.ex.pay.bizvo.BizBaseRequest;
import com.song.ex.pay.bizvo.result.PayBaseResult;
import com.song.ex.pay.cfactory.PayChannelFactory;
import com.song.ex.pay.service.BindCardService;

/**
 * com.song.ex.pay.controller.hanlder
 *
 * @author by Song
 * @date 2019/9/29 16:52
 */
public class BindCardConfHandler extends BaoFooAbstractHandler {

    //PayAbstractHandler -- 进行模板模式限定 - 第一步参数检验，第二步验签，第三步实例方法调用-此处是抽象方法，第四步可以进行统一状态处理
    //如果各状态不一致不好判断，可以交给子类进行处理

    @Override
    public PayBaseResult doRequestThreeInterface(BizBaseRequest bizBaseRequest, String payChannelCode) {
        //通过工厂模式拿到对应实例化对象，进行方法调用
        //产品code-对应到
        BindCardService bindCardService = PayChannelFactory.newInstanceBindCard(payChannelCode);
        return bindCardService.bindCard(bizBaseRequest);
    }





}
