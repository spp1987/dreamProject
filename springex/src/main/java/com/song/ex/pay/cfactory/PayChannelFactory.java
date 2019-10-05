package com.song.ex.pay.cfactory;

import com.song.ex.pay.common.BizException;
import com.song.ex.pay.service.BaoFoo.BaoFooBindCardService;
import com.song.ex.pay.service.BindCardService;
import com.song.ex.pay.service.yeepay.YeePayBindCardService;

/**
 * com.song.ex.pay.cfactory
 *
 * @author by Song
 * @date 2019/9/29 16:03
 */
public class PayChannelFactory {

    /**
     * 通过-配置文件、db
     * 来获取
     * @param channelCode 渠道code
     */
    public static BindCardService newInstanceBindCard(String channelCode){
        if("BAOFOO".equals(channelCode)){
            return new BaoFooBindCardService();
        }else if("YEEPAY".equals(channelCode)){
            return new YeePayBindCardService();
        }
        throw new BizException("暂不支持此绑卡渠道 {}",channelCode);
    }

    public static void main(String[] args)throws Exception {
        String className =  "com.song.ex.pay.service.BaoFoo.BaoFooBindCardService";
        Class clazz = Class.forName(className);
        

    }
}
