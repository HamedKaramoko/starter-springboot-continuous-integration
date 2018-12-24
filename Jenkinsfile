pipeline {
  agent {
    docker {
      image 'maven:latest'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('mavenTest') {
      post {
        always {
          junit 'target/surefire-reports/*.xml'

        }

      }
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Admin check') {
      steps {
        input 'Do you want to go further?'
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
      mail(to: 'hamed.karamoko.hk@outlook.com', subject: "Succedded Pipeline: ${currentBuild.fullDisplayName}", body: "All is right with ${env.BUILD_URL}")

    }

  }
}