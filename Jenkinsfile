pipeline {
    agent { docker { image 'maven:3.6.0-jdk-8' } }
    stages {
        stage('test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}