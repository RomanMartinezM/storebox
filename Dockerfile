# Build stage
FROM maven:3.8.7 AS build
WORKDIR /app
COPY pom.xml .
# Download dependencies first to leverage Docker cache
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR
COPY --from=build /app/target/*.jar app.jar

# Set environment variables with defaults
ENV TZ=America/Mexico_City \
    SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8080

# Expose the port the app runs on
EXPOSE ${SERVER_PORT}

# Create a non-root user and switch to it
RUN addgroup -S -g 1001 appuser && \
    adduser -S -u 1001 -G appuser -s /bin/false -D appuser && \
    chown -R appuser:appuser /app

USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1

# Command to run the application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
