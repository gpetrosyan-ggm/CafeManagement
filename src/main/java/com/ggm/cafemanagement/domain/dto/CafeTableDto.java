package com.ggm.cafemanagement.domain.dto;

import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.domain.entity.User;
import com.ggm.cafemanagement.domain.enums.TableStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CafeTableDto {

    private Long id;

    @NotBlank(message = "Table name is required")
    private String tableName;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private TableStatusEnum status;

    private User waiter;

    private List<Order> orders;

}
