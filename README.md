# Path to Run
## Build from Scratch
Assuming you have the following dependencies:
* Maven 3.6 or later
* Node 11 or later
* Java JDK 11 or later

Set the following environment variables:

|Environment Var|Value|
|---|---|
|`SPRING_PROFILES_ACTIVE`|`local`|
|`STRAVA_CLIENT_ID`|See [developers.strava.com](https://developers.strava.com)|
|`STRAVA_CLIENT_SECRET`|See [developers.strava.com](https://developers.strava.com)|
|`STRAVA_CLIENT_TOKEN`|See [developers.strava.com](https://developers.strava.com)|

Run a local Postgres server with Docker:
```
docker container run -d \
    -p 5432:5432 \
    -e POSTGRES_USER=pathtorun \
    -e POSTGRES_PASSWORD=abc123 \
    -e POSTGRES_DB=pathtorundb \
    --name path-to-run-db \
    postgres:latest
```

Build client:
```
cd client
yarn
yarn build
```

Build server:
```
cd server
mvn clean install
```

Run application on 8080:
```
cd server
java -jar target/*.jar
```
