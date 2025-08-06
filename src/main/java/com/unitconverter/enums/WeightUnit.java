package com.unitconverter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing weight units
 */
public enum WeightUnit {
    GRAM("gram"),
    KILOGRAM("kilogram"),
    POUND("pound"),
    OUNCE("ounce"),
    TON("ton");

    private final String value;

    WeightUnit(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static WeightUnit fromString(String value) {
        for (WeightUnit unit : WeightUnit.values()) {
            if (unit.value.equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unknown weight unit: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}