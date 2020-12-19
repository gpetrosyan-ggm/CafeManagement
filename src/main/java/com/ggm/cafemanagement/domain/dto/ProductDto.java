package com.ggm.cafemanagement.domain.dto;

import com.ggm.cafemanagement.domain.entity.ProductInOrder;
import com.ggm.cafemanagement.domain.enums.ProductCategoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "100.0", message = "Min price should be 100.")
    private BigDecimal price;

    private ProductInOrder productInOrder;

}
