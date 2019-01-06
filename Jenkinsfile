pipeline {
  agent {
    docker {
      image 'maven:latest'
      args '-v /root/.m2:/root/.m2'
    }
  }
  environment {
        TAG=1.0
    }
  stages {
    
    stage('Docker image build') {
      agent { docker { image 'docker:stable-dind' } }
      steps {
        sh 'docker build -t continuous-integration .'
        sh 'docker tag continuous-integration hamedkaramoko/continuous-integration:1.0'
        sh 'docker push hamedkaramoko/continuous-integration:1.0'
      }
    }
    stage('Docker deploy') {
      agent { docker { image 'docker:stable-dind' } }
      steps {
        sh 'docker run --rm hamed/karamoko/continuous-integration:1.0'
      }
    }
  }
  post {
        success {
            echo 'Your build is successfully ended.'
        }
        failure {
            echo 'Your build is done with failure.'
        }
        unstable {
            echo 'You made an unstable pipeline state.'
        }
    }
}