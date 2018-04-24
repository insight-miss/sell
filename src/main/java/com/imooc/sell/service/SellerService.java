package com.imooc.sell.service;

import com.imooc.sell.dataObject.SellerInfo;

/**
 * 卖家端
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenId(String openid);
}
