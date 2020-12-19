package com.ggm.cafemanagement.repository;

import com.ggm.cafemanagement.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

}
