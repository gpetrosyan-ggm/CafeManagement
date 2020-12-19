package com.ggm.cafemanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductInOrderStatusEnum {

    ACTIVE("Active"),
    CANCELLED("Cancelled");

    private final String value;

    ProductInOrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ProductInOrderStatusEnum of(String value) {
        for (ProductInOrderStatusEnum b : ProductInOrderStatusEnum.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("");
    }

}
