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
    stage('mavenTest') {
    	steps {
	        sh 'mvn -DskipTests=false clean test'
	    }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }

      }
    }
    stage('mavenBuild') {
      steps {
        sh 'mvn -B verify'
      }
    }
    stage('Sonar analysis') {
      steps {
        withSonarQubeEnv('Jenkins_Continuous_Integration') {
          sh 'mvn sonar:sonar'
        }

      }
    }
    stage('Docker checking') {
      steps {
        sh 'ls -l'
        sh 'docker info'
        sh 'docker --version'
        sh 'docker build --help'
      }
    }
    stage('Docker image build') {
      steps {
        sh 'docker build -t continuous-integration .'
        sh 'docker tag continuous-integration hamedkaramoko/continuous-integration:$TAG'
        sh 'docker push hamedkaramoko/continuous-integration:$TAG'
      }
    }
    stage('Docker deploy') {
      steps {
        sh 'docker run --rm hamed/karamoko/continuous-integration:$TAG'
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