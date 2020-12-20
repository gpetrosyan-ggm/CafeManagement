package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.ProductInOrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_in_order")
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductInOrderStatusEnum status;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
