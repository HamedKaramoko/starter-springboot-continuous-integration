pipeline {
    agent { docker { image 'maven:3.6.0-jdk-8' } }
    stages {
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('build') {
            steps {
                sh 'mvn package'
            }
        }
    }
}