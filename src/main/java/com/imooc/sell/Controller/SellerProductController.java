package com.imooc.sell.Controller;

import com.imooc.sell.dataObject.ProductCategory;
import com.imooc.sell.dataObject.ProductInfo;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.ProductForm;
import com.imooc.sell.service.CateGoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品信息
 */

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CateGoryService cateGoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                             @RequestParam(value = "size" , defaultValue = "3") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    @RequestMapping("on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        ProductInfo productInfo = null;
        try{
            productInfo = productService.onSale(productId);
        }catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/erro",map);
        }

        map.put("msg","商品上架");
        map.put("url","/sell/seller/product/list");
        map.put("productInfo",productInfo);
        return new ModelAndView("common/success",map);
    }

    @RequestMapping("off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        ProductInfo productInfo = null;
        try{
            productInfo = productService.offSale(productId);
        }catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/erro",map);
        }

        map.put("msg","商品下架");
        map.put("url","/sell/seller/product/list");
        map.put("productInfo",productInfo);
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = cateGoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/erro",map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }

            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/erro",map);
        }

        map.put("msg","商品信息修改成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
