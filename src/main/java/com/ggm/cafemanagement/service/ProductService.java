package com.ggm.cafemanagement.service;

import com.ggm.cafemanagement.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    void save(ProductDto productDto);

}
