package com.imooc.sell.handler;

import com.imooc.sell.Vo.ResultVO;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultVO handerSellerException(SellException e) {
        return ResultVOUtil.eeror(e.getCode(),e.getMessage());
    }
}
