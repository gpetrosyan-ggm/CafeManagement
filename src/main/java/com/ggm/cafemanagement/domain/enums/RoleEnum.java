package com.ggm.cafemanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleEnum {

    WAITER("Waiter"),
    MANAGER("Manager");

    private final String value;

    RoleEnum(String value) {
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
    public static RoleEnum of(String value) {
        for (RoleEnum b : RoleEnum.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("");
    }

}
