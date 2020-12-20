package com.ggm.cafemanagement.repository;

import com.ggm.cafemanagement.domain.entity.ProductInOrder;

import java.util.Optional;

public interface ProductInOrderRepository {

    void save(ProductInOrder productInOrderDto);

    void update(ProductInOrder productInOrderDto);

    Optional<ProductInOrder> findById(Long id);

}
