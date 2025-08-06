package com.unitconverter.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response model for unit conversion
 */
@Schema(description = "Unit conversion response")
public class ConversionResponse {

    @Schema(description = "The converted value", example = "77.0")
    private Double convertedValue;

    @Schema(description = "The unit the value was converted to", example = "fahrenheit")
    private String convertedUnit;

    @Schema(description = "Explanation or formula used for conversion", example = "°F = (°C × 9/5) + 32")
    private String explanation;

    @Schema(description = "The original input request")
    private ConversionRequest originalInput;

    @Schema(description = "Status message", example = "Conversion successful")
    private String status;

    // Default constructor
    public ConversionResponse() {}

    // Constructor with all fields
    public ConversionResponse(Double convertedValue, String convertedUnit, String explanation, 
                            ConversionRequest originalInput, String status) {
        this.convertedValue = convertedValue;
        this.convertedUnit = convertedUnit;
        this.explanation = explanation;
        this.originalInput = originalInput;
        this.status = status;
    }

    // Builder pattern for easier object creation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Double convertedValue;
        private String convertedUnit;
        private String explanation;
        private ConversionRequest originalInput;
        private String status;

        public Builder convertedValue(Double convertedValue) {
            this.convertedValue = convertedValue;
            return this;
        }

        public Builder convertedUnit(String convertedUnit) {
            this.convertedUnit = convertedUnit;
            return this;
        }

        public Builder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public Builder originalInput(ConversionRequest originalInput) {
            this.originalInput = originalInput;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public ConversionResponse build() {
            return new ConversionResponse(convertedValue, convertedUnit, explanation, originalInput, status);
        }
    }

    // Getters and Setters
    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getConvertedUnit() {
        return convertedUnit;
    }

    public void setConvertedUnit(String convertedUnit) {
        this.convertedUnit = convertedUnit;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public ConversionRequest getOriginalInput() {
        return originalInput;
    }

    public void setOriginalInput(ConversionRequest originalInput) {
        this.originalInput = originalInput;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConversionResponse{" +
                "convertedValue=" + convertedValue +
                ", convertedUnit='" + convertedUnit + '\'' +
                ", explanation='" + explanation + '\'' +
                ", originalInput=" + originalInput +
                ", status='" + status + '\'' +
                '}';
    }
}