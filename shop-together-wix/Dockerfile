# Use Maven and Java 17 to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a smaller base image to run the built app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose port (optional for local)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
