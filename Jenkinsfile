pipeline {
    environment {
        PROJECT_NAME = 'path-to-run'
        DEV_ENVIRONMENT_PORT = '9001'

        STRAVA_CLIENT_ID = credentials('strava-client-id')
        STRAVA_CLIENT_SECRET = credentials('strava-client-secret')
        STRAVA_REFRESH_TOKEN = credentials('strava-refresh-token')

        POSTGRES_HOST = 'localhost'
        POSTGRES_PORT = '9002'
        POSTGRES_USERNAME = credentials('path-to-run-postgres-username')
        POSTGRES_PASSWORD = credentials('path-to-run-postgres-password')
    }
    agent none
    stages {
        stage('Build and Test') {
            agent {
                docker {
                    image 'maven:3.6-jdk-11'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh '''
                    mvn clean install \
                        -DSTRAVA_CLIENT_ID=${STRAVA_CLIENT_ID} \
                        -DSTRAVA_CLIENT_SECRET=${STRAVA_CLIENT_SECRET} \
                        -DSTRAVA_REFRESH_TOKEN=${STRAVA_REFRESH_TOKEN}
                '''
            }
        }
        stage('Archive Artifact') {
            agent any
            steps {
                archiveArtifacts 'target/*.jar'
            }
        }
        stage('Build Docker Image') {
            agent any
            steps {
                sh 'docker build -t ${PROJECT_NAME}:latest .'
                sh 'docker image prune -f'
            }
        }
        stage('Deploy to Dev Environment') {
            agent any
            steps {
                sh 'docker container stop ${PROJECT_NAME}'
                sh 'docker container rm ${PROJECT_NAME}'
                sh '''
                    docker run -d \
                    -e STRAVA_CLIENT_ID=${STRAVA_CLIENT_ID} \
                    -e STRAVA_CLIENT_SECRET=${STRAVA_CLIENT_SECRET} \
                    -e STRAVA_REFRESH_TOKEN=${STRAVA_REFRESH_TOKEN} \
                    -e POSTGRES_HOST=${POSTGRES_HOST} \
                    -e POSTGRES_PORT=${POSTGRES_PORT} \
                    -e POSTGRES_USERNAME=${POSTGRES_USERNAME} \
                    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                    -p ${DEV_ENVIRONMENT_PORT}:8080 \
                    --net=${PROJECT_NAME}-net
                    --name ${PROJECT_NAME} \
                    ${PROJECT_NAME}:latest

                '''
            }
        }
    }
}
