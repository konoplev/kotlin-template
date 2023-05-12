FROM openjdk:17-jdk-slim as builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle
RUN ./gradlew --no-daemon build -x test

COPY src /app/src
RUN ./gradlew --no-daemon bootJar

FROM eclipse-temurin:17-jre-alpine

RUN apk upgrade --no-cache libssl3 libcrypto3

ARG UID=10000
ARG GID=1000

WORKDIR /app

COPY --chmod=755 --from=builder /app/build/libs/*.jar /app/app.jar

USER ${UID}:${GID}

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
