package com.unitconverter.service;

import com.unitconverter.enums.LengthUnit;
import com.unitconverter.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

/**
 * Service for length unit conversions
 */
@Service
public class LengthService {

    // Conversion factors to meters
    private static final double METER_TO_METER = 1.0;
    private static final double CENTIMETER_TO_METER = 0.01;
    private static final double MILLIMETER_TO_METER = 0.001;
    private static final double KILOMETER_TO_METER = 1000.0;
    private static final double INCH_TO_METER = 0.0254;
    private static final double FOOT_TO_METER = 0.3048;
    private static final double YARD_TO_METER = 0.9144;
    private static final double MILE_TO_METER = 1609.344;

    public double convert(String fromUnit, String toUnit, double value) {
        LengthUnit from = LengthUnit.fromString(fromUnit);
        LengthUnit to = LengthUnit.fromString(toUnit);
        
        if (from == to) {
            return value;
        }
        
        // Convert to meters first, then to target unit
        double meters = toMeters(from, value);
        return fromMeters(to, meters);
    }

    public String getConversionFormula(String fromUnit, String toUnit) {
        LengthUnit from = LengthUnit.fromString(fromUnit);
        LengthUnit to = LengthUnit.fromString(toUnit);
        
        if (from == to) {
            return "Same unit, no conversion needed";
        }
        
        double fromFactor = getConversionFactor(from);
        double toFactor = getConversionFactor(to);
        double conversionFactor = fromFactor / toFactor;
        
        return String.format("1 %s = %.6f %s", fromUnit, conversionFactor, toUnit);
    }

    private double toMeters(LengthUnit from, double value) {
        return value * getConversionFactor(from);
    }

    private double fromMeters(LengthUnit to, double meters) {
        return meters / getConversionFactor(to);
    }

    private double getConversionFactor(LengthUnit unit) {
        return switch (unit) {
            case METER -> METER_TO_METER;
            case CENTIMETER -> CENTIMETER_TO_METER;
            case MILLIMETER -> MILLIMETER_TO_METER;
            case KILOMETER -> KILOMETER_TO_METER;
            case INCH -> INCH_TO_METER;
            case FOOT -> FOOT_TO_METER;
            case YARD -> YARD_TO_METER;
            case MILE -> MILE_TO_METER;
        };
    }
}