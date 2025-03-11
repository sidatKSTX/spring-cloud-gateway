# Stage 1: Build the application
FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /api_gateway_service
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM amazoncorretto:21-alpine
WORKDIR /app-spring-boot-admin
COPY --from=build /api_gateway_service/target/*.jar api_gateway_service.jar

# Use a minimal command to run the application
ENTRYPOINT ["java", "-jar", "api_gateway_service.jar"
