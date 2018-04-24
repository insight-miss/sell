package com.imooc.sell.service.impl;

import com.imooc.sell.dataObject.ProductCategory;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategGoryServiceImplTest {

    @Autowired
    private CategGoryServiceImpl categGoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categGoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categGoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = categGoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,10));
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("ts",9);
        ProductCategory result = categGoryService.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void test(){

    }
}