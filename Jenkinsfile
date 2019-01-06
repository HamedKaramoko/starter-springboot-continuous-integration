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
    stage('Pre Docker image build') {
      steps {
      	sh 'pwd'
        sh 'ls -la'
      }
    }
    stage('Docker image build') {
      agent { docker { 
      	customWorkspace '/root/.jenkins/workspace/ot-continuous-integration_master'
      	image 'docker:stable-dind' } }
      steps {
        sh 'docker build -t continuous-integration .'
        sh 'docker tag continuous-integration hamedkaramoko/continuous-integration:1.0'
        sh 'docker push hamedkaramoko/continuous-integration:1.0'
      }
    }
    stage('Docker deploy') {
      agent { docker { 
      	customWorkspace '/root/.jenkins/workspace/ot-continuous-integration_master'
      	image 'docker:stable-dind' } }
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