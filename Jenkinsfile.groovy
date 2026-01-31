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

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }
    }

    post {

        always {
            echo 'Archiving test artifacts...'
            archiveArtifacts artifacts: '**/test-output/**, **/reports/**, **/screenshots/**', allowEmptyArchive: true
        }

        success {
            echo 'Tests executed successfully ✅'
        }

        failure {
            echo 'Tests failed ❌'
        }

        cleanup {
            cleanWs()
        }
    }
}
