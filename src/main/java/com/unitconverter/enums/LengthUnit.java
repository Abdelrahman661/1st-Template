package com.unitconverter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing length units
 */
public enum LengthUnit {
    METER("meter"),
    CENTIMETER("centimeter"),
    MILLIMETER("millimeter"),
    KILOMETER("kilometer"),
    INCH("inch"),
    FOOT("foot"),
    YARD("yard"),
    MILE("mile");

    private final String value;

    LengthUnit(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static LengthUnit fromString(String value) {
        for (LengthUnit unit : LengthUnit.values()) {
            if (unit.value.equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unknown length unit: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}