# 1단계: Build
FROM gradle:8.5.0-jdk21 AS builder
WORKDIR /app
COPY .. .
WORKDIR /app/app-api
RUN gradle bootJar --no-daemon

# 2단계: Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/app-api/build/libs/*.jar app-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-api.jar"]
