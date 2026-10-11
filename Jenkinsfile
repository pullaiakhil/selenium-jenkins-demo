pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    options {
        timestamps()
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests compile'
            }
        }

        stage('Selenium Tests') {
            steps {
                bat 'mvn -B test'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/surefire-reports/*', allowEmptyArchive: true
        }
        success {
            echo 'Build and Selenium tests completed successfully.'
        }
        failure {
            echo 'Build or Selenium tests failed. Check the console output and test report.'
        }
    }
}
