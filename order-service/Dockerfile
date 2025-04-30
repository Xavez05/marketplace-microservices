FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar order-service-0.0.1-SNAPSHOT.jar

EXPOSE 8085

CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]
