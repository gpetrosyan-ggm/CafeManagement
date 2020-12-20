package com.ggm.cafemanagement.domain.dto;

import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.domain.entity.Product;
import com.ggm.cafemanagement.domain.enums.ProductInOrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductInOrderDto {

    private Long id;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount should min be 1.")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private ProductInOrderStatusEnum status;

    private String comment;

    private Order order;

    private Product product;

    private Long orderId;

    private Long productId;

}
