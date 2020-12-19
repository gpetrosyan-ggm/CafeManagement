package com.ggm.cafemanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatusEnum {

    OPEN("Open"),
    CANCELLED("Cancelled"),
    CLOSED("Closed");

    private final String value;

    OrderStatusEnum(String value) {
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
    public static OrderStatusEnum of(String value) {
        for (OrderStatusEnum b : OrderStatusEnum.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("");
    }

}
