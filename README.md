
# Project Documentation

This documentation provides an overview of the project, including instructions to run the code and an API documentation for the microservices.

## Project Overview

The project consists of two microservices: a phone number validation service and a customer CRUD service. The phone number validation service integrates with a third-party service called Numverify to validate phone numbers. The customer CRUD service allows performing CRUD (Create, Read, Update, Delete) operations on customer data. 
The frontend is built with React to provide a user interface for performing the CRUD operations.

## Running the Code

To run the project, follow these steps:

1. Clone the repository from GitHub: `git clone <repository-url>`.
2. Install the necessary dependencies for each microservice:
   - Phone Number Validation Service:
     - Navigate to the `numverify-api` directory.
     - Run `mvn install` to install the required dependencies.
   - Customer CRUD Service:
     - Navigate to the `customer-crud-service` directory.
     - Run `mvn install` to install the required dependencies.
   - Web UI:
     - Navigate to the `customer-api` directory.
     - Run `npm install` to install the required dependencies.
3. Configure the project:
   - Phone Number Validation Service:
     - Open the `numverify-api/src/main/resources/application.properties` file and configure the third party API connection properties.
   - Customer CRUD Service:
     - Open the `customer-crud-service/src/main/resources/application.properties` file and configure the database connection properties.
   - Web UI:
     - Open the `customer-web/http-common.js` file and provide the necessary API configuration details.
4. Start the applications:
   - Web:
     - Run `npm start` to start the frontend.
   - Phone Number Validation Service and customer-api:
     - Run `mvn spring-boot:run` in the `customer-crud-service` directory.

Once the microservices and frontend are running, you can access the application by opening your browser and navigating to `http://localhost:3000`.

## API Documentation

### Phone Number Validation Service

#### Endpoint: POST /customers

**Request Body:**

```json
{
  "phoneNumber": "14158586273"
}
```

**Response:**

```json
{
    "id": 22,
    "name": "Sawsan",
    "address": "ksd",
    "number": "14158586273",
    "countryCode": "US",
    "countryName": "United States of America",
    "carrier": "AT&T Mobility LLC"
}
```

### Customer CRUD Service

#### Endpoint: GET /customers/{id}

**Response:**

```json
{
    "id": 22,
    "name": "Sawsan",
    "address": "ksd",
    "number": "14158586273",
    "countryCode": "US",
    "countryName": "United States of America",
    "carrier": "AT&T Mobility LLC"
}
```

#### Endpoint: PUT /customers/{id}

**Request Body:**

```json
{
  "name": "John Doe",
  "address": "test address",
  "number": "+1234567890"
}
```

**Response:**

```json
{
    "id": 22,
    "name": "John Doe",
    "address": "test address",
    "number": "+1234567890",
    "countryCode": "US",
    "countryName": "United States of America",
    "carrier": "AT&T Mobility LLC"
}
```

#### Endpoint: DELETE /customers/{id}

**Response:**

```
Status: 204 No Content
```

## Code Efficiency and Improvements

In the codebase, there are parts that are highly efficient and well-optimized. These include:

- All nominal operaions in the API have unit tests.
- Proper handling and logging of errors or exceptions to ensure the stability and reliability of the services.

However, there are also areas that could benefit from more time investment to improve efficiency and code quality:

- Security/validation enhancements: Implement proper input validation, sanitization, and security measures to protect against common vulnerabilities.
- Scalability considerations: Evaluate the system's scalability and plan for potential limitations. Consider implementing features like being able to integrate different third party services dynamically.
- Code organization and documentation: Ensure the codebase follows good coding practices, maintainable structure, and proper documentation. Improve code readability and maintainability by adding inline comments, using descriptive function and variable names, and applying coding style conventions consistently.

Happy coding!
