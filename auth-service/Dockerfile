FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]
