package com.song.ex.pay.bizvo.request;

import com.song.ex.pay.bizvo.BizBaseRequest;
import lombok.Data;

/**
 * com.song.ex.pay.bizvo.request
 *
 * @author by Song
 * @date 2019/9/27 11:54
 */
@Data
public class BizBindCardConfRequest extends BizBaseRequest {


    private String bindCardRequestNo;

    private String validateCode;

}
