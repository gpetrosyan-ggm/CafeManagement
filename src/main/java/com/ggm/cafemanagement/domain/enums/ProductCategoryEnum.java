package com.ggm.cafemanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductCategoryEnum {

    // The most demand group
    COFFEE("Coffee"),
    TEA("tea"),
    BEAR("Beer"),
    PASTRY("Pastry"),
    ICE_CREAM("Ice cream"),

    // Drinks group
    NO_ALCOHOLIC_DRINKS("No alcoholic drinks"),
    ALCOHOLIC_DRINKS("Alcoholic drinks"),
    NATURAL_DRINKS("Natural drinks"),
    CARBONATED_DRINKS("Carbonated drinks"),

    // Dish group
    COLD_DISH("Cold dish"),
    HOT_DISH("Hot dish"),

    // Other group
    PRODUCES("Produces"),
    MEATS("Meats"),
    SEAFOOD("Seafood");

    private final String value;

    ProductCategoryEnum(String value) {
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
    public static ProductCategoryEnum of(String value) {
        for (ProductCategoryEnum b : ProductCategoryEnum.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("");
    }

}
