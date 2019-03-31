FROM openjdk:11-stretch
MAINTAINER George Black <contact@georgeblack.me>

RUN mkdir /app

COPY path-to-run-server/target/*.jar /app/path-to-run.jar

CMD ["java", "-jar", "/app/path-to-run.jar"]
EXPOSE 8080
