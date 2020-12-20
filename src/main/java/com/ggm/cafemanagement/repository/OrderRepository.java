package com.ggm.cafemanagement.repository;

import com.ggm.cafemanagement.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    void update(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

}
