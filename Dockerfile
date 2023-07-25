FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN ./gradlew build
ENTRYPOINT ["./gradlew","bootRun"]
EXPOSE 8080
