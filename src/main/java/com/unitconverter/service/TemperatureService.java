package com.unitconverter.service;

import com.unitconverter.enums.TemperatureUnit;
import com.unitconverter.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

/**
 * Service for temperature unit conversions
 */
@Service
public class TemperatureService {

    public double convert(String fromUnit, String toUnit, double value) {
        TemperatureUnit from = TemperatureUnit.fromString(fromUnit);
        TemperatureUnit to = TemperatureUnit.fromString(toUnit);
        
        if (from == to) {
            return value;
        }
        
        // Convert to Celsius first, then to target unit
        double celsius = toCelsius(from, value);
        return fromCelsius(to, celsius);
    }

    public String getConversionFormula(String fromUnit, String toUnit) {
        TemperatureUnit from = TemperatureUnit.fromString(fromUnit);
        TemperatureUnit to = TemperatureUnit.fromString(toUnit);
        
        if (from == to) {
            return "Same unit, no conversion needed";
        }
        
        return switch (from) {
            case CELSIUS -> switch (to) {
                case FAHRENHEIT -> "°F = (°C × 9/5) + 32";
                case KELVIN -> "K = °C + 273.15";
                default -> throw new InvalidUnitException("Invalid conversion");
            };
            case FAHRENHEIT -> switch (to) {
                case CELSIUS -> "°C = (°F - 32) × 5/9";
                case KELVIN -> "K = (°F - 32) × 5/9 + 273.15";
                default -> throw new InvalidUnitException("Invalid conversion");
            };
            case KELVIN -> switch (to) {
                case CELSIUS -> "°C = K - 273.15";
                case FAHRENHEIT -> "°F = (K - 273.15) × 9/5 + 32";
                default -> throw new InvalidUnitException("Invalid conversion");
            };
        };
    }

    private double toCelsius(TemperatureUnit from, double value) {
        return switch (from) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5.0 / 9.0;
            case KELVIN -> value - 273.15;
        };
    }

    private double fromCelsius(TemperatureUnit to, double celsius) {
        return switch (to) {
            case CELSIUS -> celsius;
            case FAHRENHEIT -> (celsius * 9.0 / 5.0) + 32;
            case KELVIN -> celsius + 273.15;
        };
    }
}