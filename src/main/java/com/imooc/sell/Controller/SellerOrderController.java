package com.imooc.sell.Controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/List")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                             @RequestParam(value = "size" , defaultValue = "3") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }


    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map) {
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e) {
            log.error("[卖家取消订单] 发生异常");

            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/List");
            return new ModelAndView("common/erro",map);
        }

        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessege());
        map.put("url","/sell/seller/order/List");
        return new ModelAndView("common/success",map);
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = null;
        try{
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e) {
            log.error("[卖家查询订单] 发生异常");
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/List");
            return new ModelAndView("common/erro",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     *完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String,Object> map){
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e) {
            log.error("[卖家完结订单] 发生异常");
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/List");
            return new ModelAndView("common/erro",map);
        }

        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessege());
        map.put("url","/sell/seller/order/List");
        return new ModelAndView("common/success",map);
    }
}
