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
                sh 'mvn -e test'
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
    post {
        success {
            mail to: 'hamed.karamoko.hk@outlook.com',
             subject: "Succedded Pipeline: ${currentBuild.fullDisplayName}",
             body: "All is right with ${env.BUILD_URL}"
        }
    }

}