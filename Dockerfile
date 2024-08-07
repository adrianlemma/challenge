# Etapa 1: Compilación
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /root

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /root

COPY --from=build /root/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]