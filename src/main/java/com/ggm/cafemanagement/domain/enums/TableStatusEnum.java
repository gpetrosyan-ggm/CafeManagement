package com.ggm.cafemanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TableStatusEnum {

    BASIC("Basic"),
    STANDARD("Standard"),
    VIP("VIP");

    private final String value;

    TableStatusEnum(String value) {
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
    public static TableStatusEnum of(String value) {
        for (TableStatusEnum b : TableStatusEnum.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }

        throw new IllegalArgumentException("");
    }

}
