package com.song.ex.pay.service;

import com.song.ex.pay.bizvo.BizBaseRequest;
import com.song.ex.pay.bizvo.result.PayBaseResult;

/**
 * com.song.ex.pay.service
 *
 * @author by Song
 * @date 2019/9/29 11:40
 */
public interface BindCardService {

    PayBaseResult bindCard(BizBaseRequest bizBaseRequest);

    PayBaseResult confirmBindCard(BizBaseRequest bizBaseRequest);

}
