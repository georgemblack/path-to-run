FROM openjdk:11-stretch
MAINTAINER George Black <contact@georgeblack.me>

RUN mkdir -p /app/public

COPY path-to-run-server/target/*.jar /app/path-to-run.jar
COPY path-to-run-client/dist/* /app/public/

CMD ["java", "-jar", "/app/path-to-run.jar"]
EXPOSE 8080
