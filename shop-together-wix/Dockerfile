## Step 1: Build the app using Maven wrapper
#FROM maven:3.9.6-eclipse-temurin-21 AS build
#WORKDIR /app
#
## Copy Maven wrapper and pom.xml first for better caching
#COPY .mvn .mvn
#COPY mvnw mvnw.cmd pom.xml ./
#RUN chmod +x ./mvnw
#
## Download dependencies (cached if pom.xml doesn't change)
#RUN ./mvnw dependency:go-offline -B
#
## Copy source and build
#COPY src ./src
#RUN ./mvnw clean package -DskipTests -B
#
## Step 2: Runtime image
#FROM eclipse-temurin:21-jre-alpine
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]


# Step 1: Build the app using Maven wrapper
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy Maven wrapper and pom.xml first for better caching
COPY .mvn .mvn
COPY mvnw mvnw.cmd pom.xml ./
RUN chmod +x ./mvnw

# Download dependencies (cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests -B


# Step 2: Runtime image with HTTPS certs
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy jar
COPY --from=build /app/target/*.jar app.jar

# Copy SSL certificate and key (assumes you have them locally)
# Use PKCS12 format (keystore.p12) which Spring Boot understands
COPY keystore.p12 keystore.p12

# Expose HTTPS port
EXPOSE 8443

# Run Spring Boot with HTTPS
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8443", "--server.ssl.enabled=true", "--server.ssl.key-store=keystore.p12", "--server.ssl.key-store-password=changeit", "--server.ssl.key-store-type=PKCS12"]