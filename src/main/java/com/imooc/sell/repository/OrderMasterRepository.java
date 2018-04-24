package com.imooc.sell.repository;

import com.imooc.sell.dataObject.OrderMaster;
import com.imooc.sell.dataObject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    @Query("SELECT o from OrderMaster o where o.orderId = :id")
    OrderMaster getById(@Param("id") String orderId);

}
