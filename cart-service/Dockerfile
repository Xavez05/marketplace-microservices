FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar cart-service-0.0.1-SNAPSHOT.jar

EXPOSE 8084

CMD ["java", "-jar", "cart-service-0.0.1-SNAPSHOT.jar"]
