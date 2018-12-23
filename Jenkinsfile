pipeline {
    agent { docker { image 'maven:latest' } }
    stages {
    	stage('folder') {
            steps {
                sh 'ls -la'
            }
        }
        stage('mavenTest') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('mavenBuild') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}