FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar product-service-0.0.1-SNAPSHOT.jar

EXPOSE 8083

CMD ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]
