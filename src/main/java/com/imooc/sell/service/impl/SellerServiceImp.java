package com.imooc.sell.service.impl;

import com.imooc.sell.dataObject.SellerInfo;
import com.imooc.sell.repository.SellerInfoRepository;
import com.imooc.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openid) {
        return repository.findByOpenid(openid);
    }
}
