package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.ProductCategoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    @NotEmpty(message = "Name is required")
    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

}
