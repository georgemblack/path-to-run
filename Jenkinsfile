pipeline {
    environment {
        PROJECT_NAME = 'path-to-run'
        DEV_ENVIRONMENT_PORT = '9001'

        STRAVA_CLIENT_ID = credentials('strava-client-id')
        STRAVA_CLIENT_SECRET = credentials('strava-client-secret')
        STRAVA_REFRESH_TOKEN = credentials('strava-refresh-token')

        POSTGRES_USERNAME = credentials('path-to-run-postgres-username')
        POSTGRES_PASSWORD = credentials('path-to-run-postgres-password')
    }
    agent none
    stages {
        stage('Lint and Build Client') {
            agent {
                docker {
                    image 'node:11-stretch'
                }
            }
            stages {
                stage('Install Client Dependencies') {
                    steps {
                        dir('client') {
                            sh '''
                                npm install -g yarn
                                yarn
                            '''
                        }
                    }
                }
                stage('Lint Client') {
                    steps {
                        dir('client') {
                            sh 'yarn lint'
                        }
                    }
                }
                stage('Build Client') {
                    steps {
                        dir('client') {
                            sh 'yarn build'
                        }
                    }
                }
            }
        }
        stage('Build Server') {
            agent {
                docker {
                    image 'maven:3.6-jdk-11'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                dir('server') {
                    sh '''
                        mvn clean install \
                        -DSTRAVA_CLIENT_ID=${STRAVA_CLIENT_ID} \
                        -DSTRAVA_CLIENT_SECRET=${STRAVA_CLIENT_SECRET} \
                        -DSTRAVA_REFRESH_TOKEN=${STRAVA_REFRESH_TOKEN}
                    '''
                }
            }
        }
        stage('Archive Artifact') {
            agent any
            steps {
                archiveArtifacts 'server/target/*.jar'
            }
        }
        stage('Build Docker Image') {
            agent any
            steps {
                sh 'docker build -t ${PROJECT_NAME}:latest .'
                sh 'docker image prune -f'
            }
        }
        stage('Remove Existing Container from Dev') {
            agent any
            steps {
                sh 'docker container stop ${PROJECT_NAME} || echo "No existing container... skipping"'
                sh 'docker container rm ${PROJECT_NAME} || echo "No existing container... skipping"'
            }
        }
        stage('Deploy to Dev Environment') {
            agent any
            steps {
                sh '''
                    docker run -d \
                    -e STRAVA_CLIENT_ID=${STRAVA_CLIENT_ID} \
                    -e STRAVA_CLIENT_SECRET=${STRAVA_CLIENT_SECRET} \
                    -e STRAVA_REFRESH_TOKEN=${STRAVA_REFRESH_TOKEN} \
                    -e POSTGRES_USERNAME=${POSTGRES_USERNAME} \
                    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                    -e SPRING_PROFILES_ACTIVE=prod \
                    -p ${DEV_ENVIRONMENT_PORT}:8080 \
                    --net=${PROJECT_NAME}-net \
                    --name ${PROJECT_NAME} \
                    ${PROJECT_NAME}:latest

                '''
            }
        }
    }
}
