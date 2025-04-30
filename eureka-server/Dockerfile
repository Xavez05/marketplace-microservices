FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar eureka-server-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]
