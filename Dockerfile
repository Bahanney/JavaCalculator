# Use a Maven image with OpenJDK 17 to build the application
FROM maven:3.8-openjdk-17 as builder

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 runtime from Eclipse Temurin for the final image
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the specific shaded JAR file from the builder stage
COPY --from=builder /app/target/RaviCalculator-1.4.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]