package com.ggm.cafemanagement.domain.dto;

import com.ggm.cafemanagement.domain.entity.CafeTable;
import com.ggm.cafemanagement.domain.entity.ProductInOrder;
import com.ggm.cafemanagement.domain.entity.User;
import com.ggm.cafemanagement.domain.enums.OrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    private String comment;

    private User waiter;

    @NotNull(message = "Table is required")
    private CafeTable table;

    private List<ProductInOrder> productInOrders;

}

