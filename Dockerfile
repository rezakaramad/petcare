# -------------------------------------------------
# Build
# -------------------------------------------------

FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
RUN chmod +x gradlew
RUN ./gradlew --version && ./gradlew dependencies --no-daemon || true
COPY src src
RUN ./gradlew clean bootJar --no-daemon

# -------------------------------------------------
# Runtime
# -------------------------------------------------
FROM gcr.io/distroless/java21
WORKDIR /app
ARG JAR_FILE=app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
