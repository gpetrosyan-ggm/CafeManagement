package com.ggm.cafemanagement.service;

import com.ggm.cafemanagement.domain.dto.ProductInOrderDto;

import java.util.List;

public interface ProductInOrderService {

    List<ProductInOrderDto> findAllByOrderId(Long orderId);

    ProductInOrderDto findById(Long id);

    void create(ProductInOrderDto productInOrderDto);

    void update(ProductInOrderDto productInOrderDto);

    Long findUserId(Long orderId);

}
