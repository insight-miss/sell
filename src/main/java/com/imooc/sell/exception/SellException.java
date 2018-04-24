package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;
import lombok.Data;

@Data
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){

        super(resultEnum.getMessege());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String messeage){
        super(messeage);
        this.code=code;
    }
}
