package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.ProductInOrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_in_order")
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount should min be 1.")
    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductInOrderStatusEnum status;

    @Column(name = "comment")
    private String comment;

    @Valid
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Valid
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
