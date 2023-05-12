# Purpose

This is a template for a service written in Kotlin

# Getting Started

### Tech stack
The following technologies are used:

* [Kotlin](https://kotlinlang.org) as the main language
* [Gradle](https://docs.gradle.org) to build the project. You should have [Java17](https://www.java.com/en/download/) installed
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web) to create API endpoints
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#actuator) to add health checks
* [Spring Doc](https://springdoc.org) to generate API documentation
* [Docker](https://docker.com) to create an artifact for the deployment
* [Testcontainers](https://www.testcontainers.org/) to write integration tests
* [Docker compose](https://docs.docker.com/compose/) to run the local test environment

### Approach

All used technologies are optional. 
There are comments in the build file.
If you for example want to replace Jackson with Moshi, just find the corresponding comment, remove everything related to Jackson and add Moshi dependencies.

### Guides

#### How to build

```shell
docker build -t app .
```

#### How to run locally

```shell
docker-compose up -d &
```

It starts the service and a database

#### API documentation

To check documentation, run the app and open `http://localhost:8080/swagger-ui/index.html`. The json representation are at `http://localhost:8080/api-docs`
