package com.imooc.sell.Controller;

import com.imooc.sell.Vo.ProductInfoVO;
import com.imooc.sell.Vo.ProductVO;
import com.imooc.sell.Vo.ResultVO;
import com.imooc.sell.dataObject.ProductCategory;
import com.imooc.sell.dataObject.ProductInfo;
import com.imooc.sell.service.CateGoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CateGoryService cateGoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
//        for(ProductInfo productInfo : productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法（java8，lambda）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = cateGoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVoList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVoList.add(productVO);
        }

        return ResultVOUtil.success(productVoList);
    }
}
