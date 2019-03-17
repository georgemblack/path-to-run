pipeline {
    environment {
        PROJECT_NAME = 'path-to-run'
        DEV_ENVIRONMENT_PORT = '9001'
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
               sh 'mvn clean install'
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
                sh 'docker build -t ${PROJECT_NAME} .'
            }
        }
        stage('Deploy to Dev Environment') {
            agent any
            steps {
                sh 'docker container stop ${PROJECT_NAME}'
                sh 'docker container rm ${PROJECT_NAME}'
                sh 'docker run -d -p ${DEV_ENVIRONMENT_PORT}:8080 --name ${PROJECT_NAME} ${PROJECT_NAME}:latest'
            }
        }
    }
}
