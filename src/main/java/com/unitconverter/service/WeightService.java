package com.unitconverter.service;

import com.unitconverter.enums.WeightUnit;
import com.unitconverter.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

/**
 * Service for weight unit conversions
 */
@Service
public class WeightService {

    // Conversion factors to grams
    private static final double GRAM_TO_GRAM = 1.0;
    private static final double KILOGRAM_TO_GRAM = 1000.0;
    private static final double POUND_TO_GRAM = 453.592;
    private static final double OUNCE_TO_GRAM = 28.3495;
    private static final double TON_TO_GRAM = 1000000.0;

    public double convert(String fromUnit, String toUnit, double value) {
        WeightUnit from = WeightUnit.fromString(fromUnit);
        WeightUnit to = WeightUnit.fromString(toUnit);
        
        if (from == to) {
            return value;
        }
        
        // Convert to grams first, then to target unit
        double grams = toGrams(from, value);
        return fromGrams(to, grams);
    }

    public String getConversionFormula(String fromUnit, String toUnit) {
        WeightUnit from = WeightUnit.fromString(fromUnit);
        WeightUnit to = WeightUnit.fromString(toUnit);
        
        if (from == to) {
            return "Same unit, no conversion needed";
        }
        
        double fromFactor = getConversionFactor(from);
        double toFactor = getConversionFactor(to);
        double conversionFactor = fromFactor / toFactor;
        
        return String.format("1 %s = %.6f %s", fromUnit, conversionFactor, toUnit);
    }

    private double toGrams(WeightUnit from, double value) {
        return value * getConversionFactor(from);
    }

    private double fromGrams(WeightUnit to, double grams) {
        return grams / getConversionFactor(to);
    }

    private double getConversionFactor(WeightUnit unit) {
        return switch (unit) {
            case GRAM -> GRAM_TO_GRAM;
            case KILOGRAM -> KILOGRAM_TO_GRAM;
            case POUND -> POUND_TO_GRAM;
            case OUNCE -> OUNCE_TO_GRAM;
            case TON -> TON_TO_GRAM;
        };
    }
}