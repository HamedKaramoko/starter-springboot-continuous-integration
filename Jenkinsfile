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
    stage('Sonar analysis') {
      steps {
        withSonarQubeEnv('Jenkins_Continuous_Integration') {
          sh 'mvn sonar:sonar'
        }

      }
    }
  }
}