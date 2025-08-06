# Unit Converter REST API

A clean and organized REST API that converts units across different measurement categories (temperature, weight, length, time) built with Java, Spring Boot, Swagger, and Jakarta validation.

## Features

- **Unit Conversion**: Convert values between different units within the same category
- **Multiple Categories**: Supports temperature, length, weight, and time conversions
- **Input Validation**: Jakarta validation ensures proper request format
- **Error Handling**: Comprehensive error handling with detailed error messages
- **API Documentation**: Interactive Swagger UI for easy testing
- **Formula Explanations**: Each conversion includes the formula used

## Technologies Used

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Web MVC**
- **Jakarta Validation**
- **SpringDoc OpenAPI 3** (Swagger)
- **Maven**

## Project Structure

```
src/
├── main/
│   ├── java/com/unitconverter/
│   │   ├── controller/
│   │   │   └── ConverterController.java
│   │   ├── service/
│   │   │   ├── ConversionService.java
│   │   │   ├── TemperatureService.java
│   │   │   ├── LengthService.java
│   │   │   ├── WeightService.java
│   │   │   └── TimeService.java
│   │   ├── model/
│   │   │   ├── ConversionRequest.java
│   │   │   └── ConversionResponse.java
│   │   ├── enums/
│   │   │   ├── Category.java
│   │   │   ├── TemperatureUnit.java
│   │   │   ├── LengthUnit.java
│   │   │   ├── WeightUnit.java
│   │   │   └── TimeUnit.java
│   │   ├── exception/
│   │   │   ├── InvalidUnitException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── config/
│   │   │   └── OpenApiConfig.java
│   │   └── UnitConverterApplication.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/com/unitconverter/
```

## API Endpoints

### 1. POST /api/v1/convert
Converts a value from one unit to another within the same category.

**Request Body:**
```json
{
  "category": "temperature",
  "fromUnit": "celsius",
  "toUnit": "fahrenheit",
  "value": 25
}
```

**Response:**
```json
{
  "convertedValue": 77.0,
  "convertedUnit": "fahrenheit",
  "explanation": "°F = (°C × 9/5) + 32",
  "originalInput": {
    "category": "temperature",
    "fromUnit": "celsius",
    "toUnit": "fahrenheit",
    "value": 25.0
  },
  "status": "Conversion successful"
}
```

### 2. GET /api/v1/categories
Returns all available unit categories.

**Response:**
```json
["temperature", "length", "weight", "time"]
```

### 3. GET /api/v1/units?category={category}
Returns supported units for a given category.

**Example:** `/api/v1/units?category=temperature`

**Response:**
```json
["celsius", "fahrenheit", "kelvin"]
```

### 4. GET /api/v1/sample-payload
Returns a sample request body for testing the `/convert` endpoint.

**Response:**
```json
{
  "category": "temperature",
  "fromUnit": "celsius",
  "toUnit": "fahrenheit",
  "value": 25.0
}
```

### 5. GET /api/v1/health
Simple health check endpoint.

**Response:**
```json
{
  "status": "Unit Converter API is up and running"
}
```

## Supported Units

### Temperature
- `celsius` - Celsius (°C)
- `fahrenheit` - Fahrenheit (°F)
- `kelvin` - Kelvin (K)

### Length
- `meter` - Meter (m)
- `centimeter` - Centimeter (cm)
- `millimeter` - Millimeter (mm)
- `kilometer` - Kilometer (km)
- `inch` - Inch (in)
- `foot` - Foot (ft)
- `yard` - Yard (yd)
- `mile` - Mile (mi)

### Weight
- `gram` - Gram (g)
- `kilogram` - Kilogram (kg)
- `pound` - Pound (lb)
- `ounce` - Ounce (oz)
- `ton` - Ton (t)

### Time
- `second` - Second (s)
- `minute` - Minute (min)
- `hour` - Hour (hr)
- `day` - Day
- `week` - Week
- `month` - Month (average 30.44 days)
- `year` - Year (average 365.25 days)

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd unit-converter-api
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - API Documentation: `http://localhost:8080/api-docs`

### Testing the API

You can test the API using the provided test script:

```bash
chmod +x test-api.sh
./test-api.sh
```

Or manually test endpoints using curl:

```bash
# Health check
curl -X GET http://localhost:8080/api/v1/health

# Get all categories
curl -X GET http://localhost:8080/api/v1/categories

# Convert temperature
curl -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "temperature",
    "fromUnit": "celsius",
    "toUnit": "fahrenheit",
    "value": 25
  }'
```

## Example Conversions

### Temperature Conversion
```bash
curl -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{"category":"temperature","fromUnit":"celsius","toUnit":"fahrenheit","value":25}'
```
**Result:** 25°C = 77.0°F

### Length Conversion
```bash
curl -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{"category":"length","fromUnit":"centimeter","toUnit":"meter","value":100}'
```
**Result:** 100 cm = 1.0 m

### Weight Conversion
```bash
curl -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{"category":"weight","fromUnit":"kilogram","toUnit":"pound","value":1}'
```
**Result:** 1 kg = 2.20 lb

### Time Conversion
```bash
curl -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{"category":"time","fromUnit":"hour","toUnit":"minute","value":1}'
```
**Result:** 1 hour = 60 minutes

## Error Handling

The API provides comprehensive error handling:

### Validation Errors
```json
{
  "timestamp": "2025-08-06T01:08:55.089892209",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input parameters",
  "validationErrors": {
    "value": "Value is required",
    "category": "Category is required"
  }
}
```

### Invalid Unit Errors
```json
{
  "timestamp": "2025-08-06T01:08:55.089892209",
  "status": 400,
  "error": "Invalid Unit",
  "message": "Unknown temperature unit: invalid_unit"
}
```

## Configuration

The application can be configured via `src/main/resources/application.yml`:

```yaml
server:
  port: 8080

spring:
  application:
    name: unit-converter-api

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License.

## API Documentation

Interactive API documentation is available at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api-docs`

The Swagger UI provides a user-friendly interface to explore and test all API endpoints with example requests and responses.