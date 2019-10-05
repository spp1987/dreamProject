package com.song.ex.pay.controller.hanlder;

import com.song.ex.pay.bizvo.BizBaseResponse;
import com.song.ex.pay.bizvo.result.PayBaseResult;

/**
 * com.song.ex.pay.controller.hanlder
 *
 * @author by Song
 * @date 2019/9/30 14:54
 */
public abstract class YeePayAbstractHandler extends PayAbstractHandler{

    abstract void convertRetMessageByYeePay(PayBaseResult yeePayBaseResult, BizBaseResponse bizBaseResponse);
}
