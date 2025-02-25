FROM openjdk:21

WORKDIR /app

COPY target/Checkers-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
