pipeline {
    agent { docker { image 'maven:latest' } }
    stages {
        stage('test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}