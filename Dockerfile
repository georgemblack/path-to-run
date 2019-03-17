FROM openjdk:11-stretch
MAINTAINER George Black <contact@georgeblack.me>

ENV STRAVA_CLIENT_ID=${STRAVA_CLIENT_ID}
ENV STRAVA_CLIENT_SECRET=${STRAVA_CLIENT_SECRET}
ENV STRAVA_REFRESH_TOKEN=${STRAVA_REFRESH_TOKEN}

RUN mkdir /app

COPY target/*.jar /app/path-to-run.jar

CMD ["java", "-jar", "/app/path-to-run.jar"]
EXPOSE 8080
