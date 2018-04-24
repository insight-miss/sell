package com.imooc.sell.Controller;

import com.imooc.sell.dataObject.ProductCategory;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.CategoryForm;
import com.imooc.sell.service.CateGoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.management.ObjectName;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CateGoryService cateGoryService;


    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map) {
        List<ProductCategory> categoryList = cateGoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }


    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId" , required = false) Integer categoryId,
                              Map<String,Object> map) {
        if (categoryId!=null) {
            ProductCategory productCategory = cateGoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }

        return new ModelAndView("category/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String,Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/erro",map);
        }

        ProductCategory productCategory = new ProductCategory();

        try{

            if (categoryForm.getCategoryId() != null) {
                productCategory =  cateGoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            cateGoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/erro",map);
        }

        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
