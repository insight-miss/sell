package com.imooc.sell.repository;

import com.imooc.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    @Query("SELECT o from ProductCategory o where o.categoryId = :id")
    ProductCategory getById(@Param("id") Integer CategoryId);
}
