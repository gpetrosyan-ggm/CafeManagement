package com.ggm.cafemanagement.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AssignTableDto {

    @NotNull(message = "Table id is required")
    private Long tableId;

    @NotNull(message = "User id is required")
    private Long userId;

}
