
pipeline {
    agent any

triggers {
    pollSCM('H/2 * * * *')
}
    stages {
        stage('Check Java') {
            steps {
                bat 'java -version'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat '''
                cd /d "C:\\Users\\AKHIL PULLAI\\Downloads\\Selenium_Jenkins_Source_Code\\selenium-jenkins-demo"
                call "C:\\Users\\AKHIL PULLAI\\Downloads\\apache-maven-3.10.0-bin\\apache-maven-3.10.0\\bin\\mvn.cmd" clean test
                '''
            }
        }
    }

    post {
        success {
            echo 'Selenium tests completed successfully!'
        }
        failure {
            echo 'Selenium tests failed. Check Console Output.'
        }
    }
}
