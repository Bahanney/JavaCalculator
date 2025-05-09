# Use a Maven image with OpenJDK 17 to build the application
FROM maven:3.8-openjdk-17 as builder

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 runtime as the base image for the final image
FROM openjdk:17-jre-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
