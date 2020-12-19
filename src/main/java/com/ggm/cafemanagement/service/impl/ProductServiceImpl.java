package com.ggm.cafemanagement.service.impl;

import com.ggm.cafemanagement.domain.dto.ProductDto;
import com.ggm.cafemanagement.domain.entity.Product;
import com.ggm.cafemanagement.repository.ProductRepository;
import com.ggm.cafemanagement.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return mapper.map(products, new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public void save(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        productRepository.save(product);
    }

}
