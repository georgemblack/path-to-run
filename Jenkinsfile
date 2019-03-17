pipeline {
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
                sh 'docker build -t path-to-run .'
            }
        }
    }
}
