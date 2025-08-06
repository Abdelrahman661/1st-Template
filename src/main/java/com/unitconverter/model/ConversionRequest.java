package com.unitconverter.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request model for unit conversion
 */
@Schema(description = "Unit conversion request")
public class ConversionRequest {

    @NotBlank(message = "Category is required")
    @Schema(description = "The category of units to convert", example = "temperature", required = true)
    private String category;

    @NotBlank(message = "From unit is required")
    @Schema(description = "The unit to convert from", example = "celsius", required = true)
    private String fromUnit;

    @NotBlank(message = "To unit is required")
    @Schema(description = "The unit to convert to", example = "fahrenheit", required = true)
    private String toUnit;

    @NotNull(message = "Value is required")
    @Schema(description = "The value to convert", example = "25", required = true)
    private Double value;

    // Default constructor
    public ConversionRequest() {}

    // Constructor with all fields
    public ConversionRequest(String category, String fromUnit, String toUnit, Double value) {
        this.category = category;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.value = value;
    }

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConversionRequest{" +
                "category='" + category + '\'' +
                ", fromUnit='" + fromUnit + '\'' +
                ", toUnit='" + toUnit + '\'' +
                ", value=" + value +
                '}';
    }
}