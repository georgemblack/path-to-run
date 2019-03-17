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
        stage('Build Docker Image') {
            agent any
            steps {
                sh 'docker build -t path-to-run .'
            }
        }
    }
}
