# ---------- Stage 1: Build the application ----------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy only what's needed for Maven build
COPY pom.xml .
COPY src ./src

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests

# ---------- Stage 2: Create minimal runtime image ----------
FROM amazoncorretto:21

VOLUME /tmp

# Copy the generated JAR from the build stage
COPY --from=builder /app/target/Weather-Repoter-0.0.1.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
