package com.imooc.sell.repository;

import com.imooc.sell.dataObject.ProductCategory;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * DynamicUpdate
 * 更新时间
 */
@DynamicUpdate
public class ProductCategoryRepositoryTest {
    private final Logger log = LoggerFactory.getLogger(ProductCategoryRepositoryTest.class);
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOnetest(){
        List<ProductCategory> productCategory =repository.findAll();
        for(ProductCategory category : productCategory){
            log.info(category.toString());
        }

    }

    @Test
    public void getById(){
        ProductCategory productCategory = repository.getById(1);
        log.info(productCategory.toString());
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}