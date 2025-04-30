FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar user-service-0.0.1-SNAPSHOT.jar

EXPOSE 8082

CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
