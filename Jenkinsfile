pipeline {
  agent none
  environment {
        TAG=1.0
    }
  stages {
    stage('mavenBuild') {
    	agent {
		    docker {
		      image 'maven:latest'
		      args '-v /root/.m2:/root/.m2'
		    }
		  }
    	steps {
	        sh 'mvn verify'
	        stash includes: '**/target/*.war', name: 'warfile'
	    }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }

      }
    }
    stage('Sonar analysis') {
      agent {
	    docker {
	      image 'maven:latest'
	      args '-v /root/.m2:/root/.m2'
	    }
	  }
      steps {
        withSonarQubeEnv('Jenkins_Continuous_Integration') {
          sh 'mvn sonar:sonar'
        }

      }
    }
    stage('Docker image build') {
      agent { label ‘master’ }
      steps {
		unstash 'warfile'
        sh 'docker build -t continuous-integration .'
        sh 'docker tag continuous-integration hamedkaramoko/continuous-integration:1.0'
        sh 'docker push hamedkaramoko/continuous-integration:1.0'
      }
    }
    stage('Docker deploy') {
      agent { label ‘master’ }
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