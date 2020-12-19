package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.ProductCategoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    @NotEmpty(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Expiration date is required.")
    @Future(message = "Expiration date is past already.")
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @DecimalMin(value = "100.0", message = "Min price should be 100.")
    @Column(name = "price")
    private BigDecimal price;

    @Valid
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductInOrder productInOrder;

}
