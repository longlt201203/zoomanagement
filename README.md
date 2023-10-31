# Zoo Management
## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Deployment](#deployment)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Additional](#additional)
  - [Application Architecture](#application-architecture)
  - [Deployment Structure Design](#deployment-structure-design)
  - [Back-end Structure Design](#back-end-structure-design)
## Introduction
This is a simple Spring Boot application that serves as a template for creating web applications. 
It includes basic configuration and project structure to help you get started quickly.
## Prerequisites
Before you begin, ensure you have met the following requirements:
* Java Development Kit (JDK) 8 or higher installed (JDK 17 is recommended).
* Apache Maven installed (if you prefer to use Maven for building and managing dependencies).
## Getting Started
Follow these steps to get your Spring Boot application up and running:
1. Clone this repository to your local machine
2. Open it with your favourite IDE
3. Edit the `application.properties` (add/update some missing variables)
```
swp.zoomanagement.gcp.client-id=hello
jwt.secret=world
```
4. Build & Run the application via IDE or manually by Maven
## Deployment
Feel free to customize our `Dockerfile`
```dockerfile
FROM openjdk:17-alpine
WORKDIR /app
COPY ./out/artifacts/ZooManagement_jar .
EXPOSE 8080
CMD ["java", "-jar", "ZooManagement.jar"]
```
Build Docker container
```shell
docker build -t <registry>/<image-name>:<tag> .
```
Run the container
```shell
docker run -dp 8080:8080 <your-image>
```
## Project Structure
* `com.swp.ZooManagement` - The main package for the Zoo Management application
  * `apis` - This package contains RESTful API endpoints and controllers for managing the zoo's operations.
  * `core` - The core package holds the essential business logic and domain objects of the application
  * `errors` - This package is responsible for handling error and custom exceptions. It contains classes for defining and managing application-specific error responses.
  * `security` - The security package is used for implementing authentication and authorization mechanisms to secure the application. It includes classes for user authentication, role-based access control, and security configurations.
  * `utils` - The utils package contains utility classes and helper methods used throughout the application.
  * `ZooManagementApplication.java` - The main class of the application.
## Dependencies
1. This project includes the following dependencies:
1. Spring Boot Data JPA for database access
1. Spring Boot Security for authentication and authorization
1. Spring Boot Web for building web applications
1. Microsoft SQL Server JDBC driver for database connectivity
1. Project Lombok for reducing boilerplate code (optional)
1. Spring Boot Test for testing the application
1. Spring Security Test for security testing (test)
1. Jakarta Validation API for validation
1. Spring Boot Starter Validation for validation
1. Google API Client for working with Google APIs
1. Auth0 Java JWT for handling JSON Web Tokens (JWT)
## Additional
### Application Architecture
![](docs/images/Application%20Architecture.jpg)
### Deployment Structure Design
![](docs/images/Deployment%20Architecture.jpg)
### Back-end Structure Design
![](docs/images/Back-end%20Structure%20Design.jpg)