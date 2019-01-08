FROM tomcat:9.0-jre8
ADD target/starter-springboot-continuous-integration-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/continuous-integration.war