package com.song.ex.pay.controller;

import com.song.ex.pay.bizvo.BizBaseResponse;

/**
 * com.song.ex.pay.controller
 *
 * @author by Song
 * @date 2019/9/29 11:11
 */
public class WithholdController {

    /**
     * 代扣
     * @return
     */
    public BizBaseResponse withhold(){
        return new BizBaseResponse();
    }

    /**
     * 代扣查询
     * @return
     */
    public BizBaseResponse withholdQuery(){
        return new BizBaseResponse();
    }

    public BizBaseResponse sms() {
        return null;
    }
}
