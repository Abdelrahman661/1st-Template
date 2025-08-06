package com.unitconverter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing temperature units
 */
public enum TemperatureUnit {
    CELSIUS("celsius"),
    FAHRENHEIT("fahrenheit"),
    KELVIN("kelvin");

    private final String value;

    TemperatureUnit(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static TemperatureUnit fromString(String value) {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.value.equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unknown temperature unit: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}