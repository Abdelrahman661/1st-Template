package com.unitconverter.service;

import com.unitconverter.enums.TimeUnit;
import com.unitconverter.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

/**
 * Service for time unit conversions
 */
@Service
public class TimeService {

    // Conversion factors to seconds
    private static final double SECOND_TO_SECOND = 1.0;
    private static final double MINUTE_TO_SECOND = 60.0;
    private static final double HOUR_TO_SECOND = 3600.0;
    private static final double DAY_TO_SECOND = 86400.0;
    private static final double WEEK_TO_SECOND = 604800.0;
    private static final double MONTH_TO_SECOND = 2629746.0; // Average month (30.44 days)
    private static final double YEAR_TO_SECOND = 31556952.0; // Average year (365.25 days)

    public double convert(String fromUnit, String toUnit, double value) {
        TimeUnit from = TimeUnit.fromString(fromUnit);
        TimeUnit to = TimeUnit.fromString(toUnit);
        
        if (from == to) {
            return value;
        }
        
        // Convert to seconds first, then to target unit
        double seconds = toSeconds(from, value);
        return fromSeconds(to, seconds);
    }

    public String getConversionFormula(String fromUnit, String toUnit) {
        TimeUnit from = TimeUnit.fromString(fromUnit);
        TimeUnit to = TimeUnit.fromString(toUnit);
        
        if (from == to) {
            return "Same unit, no conversion needed";
        }
        
        double fromFactor = getConversionFactor(from);
        double toFactor = getConversionFactor(to);
        double conversionFactor = fromFactor / toFactor;
        
        return String.format("1 %s = %.6f %s", fromUnit, conversionFactor, toUnit);
    }

    private double toSeconds(TimeUnit from, double value) {
        return value * getConversionFactor(from);
    }

    private double fromSeconds(TimeUnit to, double seconds) {
        return seconds / getConversionFactor(to);
    }

    private double getConversionFactor(TimeUnit unit) {
        return switch (unit) {
            case SECOND -> SECOND_TO_SECOND;
            case MINUTE -> MINUTE_TO_SECOND;
            case HOUR -> HOUR_TO_SECOND;
            case DAY -> DAY_TO_SECOND;
            case WEEK -> WEEK_TO_SECOND;
            case MONTH -> MONTH_TO_SECOND;
            case YEAR -> YEAR_TO_SECOND;
        };
    }
}