pipeline {
    agent {
        docker {
            image 'maven:3.6-jdk-11'
        }
    }
    stages {
        stage('Build') {
            steps {
               sh 'mvn clean install'
            }
        }
    }
}
