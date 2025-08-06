package com.unitconverter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing time units
 */
public enum TimeUnit {
    SECOND("second"),
    MINUTE("minute"),
    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    YEAR("year");

    private final String value;

    TimeUnit(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static TimeUnit fromString(String value) {
        for (TimeUnit unit : TimeUnit.values()) {
            if (unit.value.equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unknown time unit: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}