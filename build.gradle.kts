import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.9.0"

    // DB
    kotlin("plugin.jpa") version "1.8.21"

    // API documentation
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
}

group = "me.konoplev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["testcontainersVersion"] = "1.18.0"

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")


    // API documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.1.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    // Healtchecks
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")

    // Mocking
    testImplementation("io.mockk:mockk:1.13.5")

    // DB
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.liquibase:liquibase-core")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

springBoot { mainClass.set("me.konoplev.template.TemplateApplicationKt") }

