#!/bin/bash

echo "Starting Unit Converter API Tests..."
echo "===================================="

# Start the application
echo "Starting Spring Boot application..."
mvn spring-boot:run > app-test.log 2>&1 &
APP_PID=$!

# Wait for application to start
echo "Waiting for application to start..."
sleep 15

# Test 1: Health endpoint
echo "Test 1: Health endpoint"
echo "GET /api/v1/health"
curl -s -X GET http://localhost:8080/api/v1/health | jq . || curl -s -X GET http://localhost:8080/api/v1/health
echo -e "\n"

# Test 2: Categories endpoint
echo "Test 2: Categories endpoint"
echo "GET /api/v1/categories"
curl -s -X GET http://localhost:8080/api/v1/categories | jq . || curl -s -X GET http://localhost:8080/api/v1/categories
echo -e "\n"

# Test 3: Units for temperature category
echo "Test 3: Units for temperature category"
echo "GET /api/v1/units?category=temperature"
curl -s -X GET "http://localhost:8080/api/v1/units?category=temperature" | jq . || curl -s -X GET "http://localhost:8080/api/v1/units?category=temperature"
echo -e "\n"

# Test 4: Sample payload endpoint
echo "Test 4: Sample payload endpoint"
echo "GET /api/v1/sample-payload"
curl -s -X GET http://localhost:8080/api/v1/sample-payload | jq . || curl -s -X GET http://localhost:8080/api/v1/sample-payload
echo -e "\n"

# Test 5: Temperature conversion
echo "Test 5: Temperature conversion (25°C to Fahrenheit)"
echo "POST /api/v1/convert"
curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "temperature",
    "fromUnit": "celsius",
    "toUnit": "fahrenheit",
    "value": 25
  }' | jq . || curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "temperature",
    "fromUnit": "celsius",
    "toUnit": "fahrenheit",
    "value": 25
  }'
echo -e "\n"

# Test 6: Length conversion
echo "Test 6: Length conversion (100 cm to meters)"
echo "POST /api/v1/convert"
curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "length",
    "fromUnit": "centimeter",
    "toUnit": "meter",
    "value": 100
  }' | jq . || curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "length",
    "fromUnit": "centimeter",
    "toUnit": "meter",
    "value": 100
  }'
echo -e "\n"

# Test 7: Weight conversion
echo "Test 7: Weight conversion (1 kg to pounds)"
echo "POST /api/v1/convert"
curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "weight",
    "fromUnit": "kilogram",
    "toUnit": "pound",
    "value": 1
  }' | jq . || curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "weight",
    "fromUnit": "kilogram",
    "toUnit": "pound",
    "value": 1
  }'
echo -e "\n"

# Test 8: Time conversion
echo "Test 8: Time conversion (1 hour to minutes)"
echo "POST /api/v1/convert"
curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "time",
    "fromUnit": "hour",
    "toUnit": "minute",
    "value": 1
  }' | jq . || curl -s -X POST http://localhost:8080/api/v1/convert \
  -H "Content-Type: application/json" \
  -d '{
    "category": "time",
    "fromUnit": "hour",
    "toUnit": "minute",
    "value": 1
  }'
echo -e "\n"

echo "===================================="
echo "API Tests Completed!"
echo "Application is running on http://localhost:8080"
echo "Swagger UI available at: http://localhost:8080/swagger-ui.html"
echo "Application PID: $APP_PID"
echo "To stop the application: kill $APP_PID"