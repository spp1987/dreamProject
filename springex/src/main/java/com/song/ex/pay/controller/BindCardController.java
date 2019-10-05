package com.song.ex.pay.controller;

import com.song.ex.pay.bizvo.BizBaseResponse;
import com.song.ex.pay.bizvo.request.BizBindCardConfRequest;
import com.song.ex.pay.bizvo.request.BizBindCardRequest;
import com.song.ex.pay.controller.hanlder.BindCardConfHandler;
import com.song.ex.pay.controller.hanlder.BindCardHandler;

/**
 * com.song.ex.pay
 *
 * @author by Song
 * @date 2019/9/27 10:42
 */
public class BindCardController {


    private BindCardHandler bindCardHandler;
    private BindCardConfHandler bindCardConfHandler;

    /**
     * 预绑卡
     * @return
     */
    public BizBaseResponse readyBindBankCard(BizBindCardRequest bindCardRequest){
        return bindCardHandler.doProcess(bindCardRequest);
    }

    /**
     * 确认绑卡
     * @return
     */
    public BizBaseResponse confirmBindBankCard(BizBindCardConfRequest bizBindCardConfRequest){
        return bindCardConfHandler.doProcess(bizBindCardConfRequest);
    }



}
