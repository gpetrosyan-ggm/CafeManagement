package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.OrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "comment")
    private String comment;

    @Valid
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User waiter;

    @Valid
    @ManyToOne
    @JoinColumn(name = "table_id")
    private CafeTable table;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductInOrder> productInOrders;

}

