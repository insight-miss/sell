package com.imooc.sell.dataObject;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
