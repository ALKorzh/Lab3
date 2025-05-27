# Lab3 - Fraction Management System

A Java-based fraction management system that implements various design patterns and follows clean architecture principles.

## Project Overview

This project is a fraction management system that demonstrates the implementation of various design patterns and software engineering principles. It's built using Java and Maven for dependency management.

## Prerequisites

- Java 24 or higher
- Maven 3.6 or higher

## Project Structure

The project follows a clean architecture approach with the following main components:

```
src/main/java/com/karzhou/fraction/
├── app/          # Application layer
├── entity/       # Domain entities
├── factory/      # Factory pattern implementations
├── repository/   # Data access layer
├── service/      # Business logic layer
├── specification/# Specification pattern implementations
├── util/         # Utility classes
├── exception/    # Custom exceptions
├── warehouse/    # Storage implementations
└── observer/     # Observer pattern implementations
```

## Building the Project

To build the project, run:

```bash
mvn clean install
```

## Running Tests

To run the tests:

```bash
mvn test
```

## Dependencies

- JUnit Jupiter 5.10.0 (for testing)

## Features

- Fraction management and operations
- Implementation of various design patterns:
  - Factory Pattern
  - Observer Pattern
  - Specification Pattern
- Clean architecture principles
- Comprehensive test coverage