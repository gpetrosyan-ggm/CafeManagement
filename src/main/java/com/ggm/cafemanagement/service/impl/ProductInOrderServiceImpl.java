package com.ggm.cafemanagement.service.impl;

import com.ggm.cafemanagement.common.exception.AccessDeniedException;
import com.ggm.cafemanagement.common.exception.NotFoundException;
import com.ggm.cafemanagement.domain.dto.ProductInOrderDto;
import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.domain.entity.Product;
import com.ggm.cafemanagement.domain.entity.ProductInOrder;
import com.ggm.cafemanagement.domain.enums.OrderStatusEnum;
import com.ggm.cafemanagement.domain.enums.ProductInOrderStatusEnum;
import com.ggm.cafemanagement.repository.OrderRepository;
import com.ggm.cafemanagement.repository.ProductInOrderRepository;
import com.ggm.cafemanagement.repository.ProductRepository;
import com.ggm.cafemanagement.service.ProductInOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public List<ProductInOrderDto> findAllByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order not found", String.format("Could not found order by id %s", orderId)));
        return mapper.map(order.getProductInOrders(), new TypeToken<List<ProductInOrderDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public ProductInOrderDto findById(Long id) {
        ProductInOrder productInOrder = productInOrderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product in order not found", String.format("Could not found product in order by id %s", id)));
        ProductInOrderDto productInOrderDto = mapper.map(productInOrder, ProductInOrderDto.class);
        productInOrderDto.setOrderId(productInOrder.getOrder().getId());
        return productInOrderDto;
    }

    @Override
    @Transactional
    public void create(ProductInOrderDto productInOrderDto) {
        Order order = orderRepository.findById(productInOrderDto.getOrderId()).orElseThrow(
                () -> new NotFoundException("Order not found", String.format("Could not found order by id %s", productInOrderDto.getOrderId())));
        if (OrderStatusEnum.OPEN != order.getStatus()) {
            throw new AccessDeniedException("Access denied to create product in order",
                    String.format("Access denied to create product in order in %s order. Order status is not OPEN.", order.getId()));
        }

        Product product = productRepository.findById(productInOrderDto.getProductId()).orElseThrow(
                () -> new NotFoundException("Product not found", String.format("Could not found product by id %s", productInOrderDto.getProductId())));

        ProductInOrder productInOrder = mapper.map(productInOrderDto, ProductInOrder.class);
        productInOrder.setStatus(ProductInOrderStatusEnum.ACTIVE);
        productInOrder.setOrder(order);
        productInOrder.setProduct(product);
        productInOrderRepository.save(productInOrder);
    }

    @Override
    @Transactional
    public void update(ProductInOrderDto productInOrderDto) {
        ProductInOrder order = mapper.map(productInOrderDto, ProductInOrder.class);
        productInOrderRepository.update(order);
    }

}
