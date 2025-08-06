package com.unitconverter.controller;

import com.unitconverter.model.ConversionRequest;
import com.unitconverter.model.ConversionResponse;
import com.unitconverter.service.ConversionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for unit conversion operations
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Unit Converter", description = "APIs for converting units across different measurement categories")
public class ConverterController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/convert")
    @Operation(summary = "Convert units", description = "Converts a value from one unit to another within the same category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conversion successful",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConversionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ConversionResponse> convert(
            @Valid @RequestBody ConversionRequest request) {
        ConversionResponse response = conversionService.convert(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories", description = "Returns all available unit categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categories retrieved successfully",
                content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = conversionService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/units")
    @Operation(summary = "Get units for category", description = "Returns a list of supported units for a given category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Units retrieved successfully",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid category",
                content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<String>> getUnits(
            @Parameter(description = "Category name", example = "temperature", required = true)
            @RequestParam String category) {
        List<String> units = conversionService.getUnitsForCategory(category);
        return ResponseEntity.ok(units);
    }

    @GetMapping("/sample-payload")
    @Operation(summary = "Get sample payload", description = "Returns a sample request body to help developers test the /convert endpoint")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sample payload retrieved successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConversionRequest.class)))
    })
    public ResponseEntity<ConversionRequest> getSamplePayload() {
        ConversionRequest sample = conversionService.getSamplePayload();
        return ResponseEntity.ok(sample);
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Simple health check to confirm the service is running")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Service is healthy",
                content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> healthStatus = new HashMap<>();
        healthStatus.put("status", "Unit Converter API is up and running");
        return ResponseEntity.ok(healthStatus);
    }
}