package com.unitconverter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing different unit categories for conversion
 */
public enum Category {
    TEMPERATURE("temperature"),
    LENGTH("length"),
    WEIGHT("weight"),
    TIME("time");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static Category fromString(String value) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}