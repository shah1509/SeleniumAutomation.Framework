pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
        BROWSER = 'chrome'
        ENV = 'qa'
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Compile') {
            steps {
                echo 'Cleaning and compiling the project'
                sh 'mvn clean compile'
            }
        }

        stage('Run Selenium TestNG Tests') {
            steps {
                echo 'Executing TestNG test suite'
                sh 'mvn test -DsuiteXmlFile=testng.xml -Dbrowser=${BROWSER} -Denv=${ENV}'
            }
        }
    }

    post {

        always {
            echo 'Archiving test artifacts (reports, screenshots, logs)'
            archiveArtifacts artifacts: '**/test-output/**, **/reports/**, **/screenshots/**, **/logs/**', allowEmptyArchive: true
        }

        success {
            echo 'Build SUCCESS - Automation tests executed successfully'
        }

        failure {
            echo 'Build FAILURE - Please check test reports'
        }

        cleanup {
            echo 'Cleaning workspace'
            cleanWs()
        }
    }
}
