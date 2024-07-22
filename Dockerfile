FROM openjdk:17-jdk

WORKDIR /app

COPY target/quizapp-0.0.1-SNAPSHOT.jar /app/quizapp.jar

EXPOSE 8080

CMD ["java", "-jar", "quizapp.jar"]