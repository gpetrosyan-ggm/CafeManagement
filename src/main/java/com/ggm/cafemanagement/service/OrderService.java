package com.ggm.cafemanagement.service;

import com.ggm.cafemanagement.domain.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAllByTableId(Long tableId);

    OrderDto findById(Long orderId);

    void create(OrderDto orderDto);

    void update(OrderDto orderDto);

}
