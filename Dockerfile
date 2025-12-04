FROM eclipse-temurin:21-jdk

LABEL maintainer="jonassavas.com"

COPY target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
