# Purpose

The Kotlin Template project is a simple starter template for building RESTful APIs using Kotlin, Spring Boot and Postgres DB. In this documentation, we will provide step-by-step instructions for creating a new project using this template, setting up a git repository, and extending the API, database, and tests.

### Approach

This project offers flexibility in choosing the technologies that best fit your needs. While the template uses popular and widely adopted technologies, they are not mandatory and can be replaced with alternatives that better suit your requirements.

All of the technologies utilized in the template are optional, providing you with the freedom to substitute them as you see fit. The build file contains comments to help you identify which technologies are employed and where they are being used. If, for instance, you prefer Moshi to Jackson, you can refer to the corresponding comment in the build file, remove all the code related to Jackson, and incorporate the necessary dependencies for Moshi.

We encourage you to explore different technologies to discover those that work best for your project. The code is modular and loosely coupled, making it easy to substitute or upgrade any of the technologies in the template.

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

### Guides

#### Creating a project from this template

Choose this repository as a `Repository template` while creating your repository to get everything except `Branch Protection` set up.

Open the project in your preferred IDE (e.g. IntelliJ IDEA). Rename the project by changing the root package name. This can be done by right-clicking on the project in the project explorer and selecting "Refactor > Rename". Enter your new package name (e.g. com.mycompany.myproject) and click "Refactor".



#### Branch Protection

To enable branch protection in your Git repository, follow these steps:

1. Navigate to your repository settings on GitHub.

2. Click on "Branches" in the left-hand menu.

3. Click "Add rule" to create a new branch protection rule.

4. Configure the branch protection settings as desired. At a minimum, we recommend requiring pull request reviews before merging and requiring status checks to pass before merging.

5. Click "Create" to save the new branch protection rule.


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


# Project Structure

The Kotlin Template project is structured using a clean architecture approach, with a focus on separation of concerns. The code is organized into distinct layers that are responsible for different tasks.

The layers of the project are as follows:

- The domain layer (`me.konoplev.template.domain`) contains the core business logic and entities. It is decoupled from any specific frameworks or technologies.
- The data layer (`me.konoplev.template.db`) is responsible for data storage and retrieval. It encapsulates the database-specific details using the `EntityTable` class, `EntityDbRepository`, and the adapter (`EntityDbRepositoryAdapter`) acts as a bridge between the data layer and the domain layer.
- The API/interface layer (`me.konoplev.template.api`) is responsible for exposing the functionality via APIs. It depends on the domain layer for retrieving the entities.


Details about the benefits:

1. **Decoupling and Separation of Concerns:** The domain layer is isolated and independent of other layers. So, for example, you can change the data storage from DB to plain files, and it doesn't impact domain. Take a note, that the data layer changes don't impact the API layer as well. The domain is independent of the API layer. So you can change from REST to GraphQL without touching anything except the API layer. Obviously API layer changes don't impact the data layer as well.
2. **Testability:** The independent layers enable easier testing. Each layer can be tested independently with the help of mocking or stubbing dependencies. 
3. **Flexibility and Maintainability:** The independent layers make the codebase more flexible and maintainable. If you need to change the database technology or framework, you can do so without affecting the domain or application logic. Similarly, if you want to change the API framework or introduce a new user interface, it can be done without modifying the business logic.
4. **Modularity and Scalability:** The independent layers promote modularity and scalability. It becomes easier to add new features, extend functionality, or introduce new components without disrupting the existing codebase. Each layer can be modified or extended independently, ensuring the system remains modular and scalable.
