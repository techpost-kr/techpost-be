# app-api/Dockerfile

FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app
COPY . .
RUN ./gradlew :app-api:bootJar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/app-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
