# REST API Practice Project

## Overview

This project is a **practice project** built to strengthen my understanding of developing RESTful APIs using Spring Boot. The primary goal of this project was to implement and experiment with various REST API concepts and best practices.

## Features

The project includes the following implementations:

- RESTful API development
- CRUD operations
- Content Negotiation (JSON/XML)
- Exception Handling with custom exceptions
- Global Exception Handling
- Input Validation
- Spring Data JPA integration
- Database persistence
- Proper HTTP Status Codes
- Layered Architecture (Controller, Service, Repository)
- DTO usage
- Clean and maintainable code structure
- HATEOAS Integration
- Swagger Integration

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database 
- Postman (API Testing)

## API Endpoints

| Method | Endpoint        | Description |
|---------|-----------------|-------------|
| GET | `/users`        | Get all users |
| GET | `/users?email=""` | Get user by ID |
| POST | `/users`        | Create a new user |
| PUT | `/users`   | Update a user |
| DELETE | `/users`   | Delete a user |

> http://localhost:8080/swagger-ui/index.html 

## Content Negotiation

This project supports content negotiation.

Examples:

```
Accept: application/json
```

```
Accept: application/xml
```

The response format is determined based on the `Accept` header sent by the client.

## Exception Handling

The project includes:

- Custom exception classes
- Global exception handler using `@RestControllerAdvice`
- Meaningful error responses
- Proper HTTP status codes

## HATEOAS 

The purpose of HATEOAS is that the user should be able to navigate through the application just with the help of single response from a api

STEP 1:  Add the Hateoas dependency in the pom file
STEP 2 : We have to make changes in the controller class alone with the help of EntityModel<DTO> class
         and WebMvcLinkBuilder and for List of models to return we have use the CollectionModel<EntityModel<DTO>>
