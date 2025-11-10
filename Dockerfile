# Build stage
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copy just the POM file first to leverage Docker cache
COPY pom.xml .

# Download dependencies with retry mechanism
RUN mvn -B dependency:go-offline || \
    (echo "First attempt failed, retrying..." && \
     mvn -B dependency:go-offline) || \
    (echo "Second attempt failed, retrying one more time..." && \
     mvn -B dependency:go-offline) || \
    (echo "Final attempt with clean..." && \
     mvn -B dependency:purge-local-repository && \
     mvn -B dependency:go-offline)

# Copy source code
COPY src ./src

# Build the application
RUN mvn -B clean package -DskipTests

# Final stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Create a non-root user and set permissions
RUN groupadd -r appuser && \
    useradd -r -g appuser -d /app -s /sbin/nologin appuser && \
    chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Health check (using wget which is more commonly available)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Command to run the application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
