FROM openjdk:11-stretch
MAINTAINER George Black <contact@georgeblack.me>

RUN mkdir -p /app/public

COPY server/target/*.jar /app/path-to-run.jar
COPY client/dist/* /app/public/

CMD ["java", "-jar", "/app/path-to-run.jar"]
EXPOSE 8080
