package com.unitconverter.service;

import com.unitconverter.enums.*;
import com.unitconverter.exception.InvalidUnitException;
import com.unitconverter.model.ConversionRequest;
import com.unitconverter.model.ConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main service for coordinating unit conversions
 */
@Service
public class ConversionService {

    @Autowired
    private TemperatureService temperatureService;
    
    @Autowired
    private LengthService lengthService;
    
    @Autowired
    private WeightService weightService;
    
    @Autowired
    private TimeService timeService;

    public ConversionResponse convert(ConversionRequest request) {
        try {
            Category category = Category.fromString(request.getCategory());
            
            double convertedValue = performConversion(category, request.getFromUnit(), 
                                                   request.getToUnit(), request.getValue());
            
            String formula = getFormula(category, request.getFromUnit(), request.getToUnit());
            
            return ConversionResponse.builder()
                    .convertedValue(convertedValue)
                    .convertedUnit(request.getToUnit())
                    .explanation(formula)
                    .originalInput(request)
                    .status("Conversion successful")
                    .build();
                    
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid category or unit: " + e.getMessage());
        } catch (Exception e) {
            throw new InvalidUnitException("Conversion failed: " + e.getMessage());
        }
    }

    public List<String> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(Category::getValue)
                .collect(Collectors.toList());
    }

    public List<String> getUnitsForCategory(String categoryName) {
        Category category = Category.fromString(categoryName);
        
        return switch (category) {
            case TEMPERATURE -> Arrays.stream(TemperatureUnit.values())
                    .map(TemperatureUnit::getValue)
                    .collect(Collectors.toList());
            case LENGTH -> Arrays.stream(LengthUnit.values())
                    .map(LengthUnit::getValue)
                    .collect(Collectors.toList());
            case WEIGHT -> Arrays.stream(WeightUnit.values())
                    .map(WeightUnit::getValue)
                    .collect(Collectors.toList());
            case TIME -> Arrays.stream(TimeUnit.values())
                    .map(TimeUnit::getValue)
                    .collect(Collectors.toList());
        };
    }

    public ConversionRequest getSamplePayload() {
        return new ConversionRequest("temperature", "celsius", "fahrenheit", 25.0);
    }

    private double performConversion(Category category, String fromUnit, String toUnit, double value) {
        return switch (category) {
            case TEMPERATURE -> temperatureService.convert(fromUnit, toUnit, value);
            case LENGTH -> lengthService.convert(fromUnit, toUnit, value);
            case WEIGHT -> weightService.convert(fromUnit, toUnit, value);
            case TIME -> timeService.convert(fromUnit, toUnit, value);
        };
    }

    private String getFormula(Category category, String fromUnit, String toUnit) {
        return switch (category) {
            case TEMPERATURE -> temperatureService.getConversionFormula(fromUnit, toUnit);
            case LENGTH -> lengthService.getConversionFormula(fromUnit, toUnit);
            case WEIGHT -> weightService.getConversionFormula(fromUnit, toUnit);
            case TIME -> timeService.getConversionFormula(fromUnit, toUnit);
        };
    }
}