# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw clean dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]