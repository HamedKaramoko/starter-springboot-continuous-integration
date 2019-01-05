pipeline {
  agent {
    docker {
      image 'maven:latest'
      args '-v /root/.m2:/root/.m2'
    }

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