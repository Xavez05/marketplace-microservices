FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar api-gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]
