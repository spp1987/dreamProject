package com.song.ex.pay.bizvo.result;

import lombok.Data;

/**
 * com.song.ex.pay.bizvo.result
 * 此类是接口方直接返回的结果 - 可以用此类接收。
 * 存放在BaseObjResponse.setObj(T)
 * @author by Song
 * @date 2019/9/29 17:00
 */
@Data
public class BindCardResult extends PayBaseResult {

    private String status;
}
