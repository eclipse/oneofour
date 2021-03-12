pipeline {
    agent any
    tools {
        maven 'apache-maven-3.6.3'
        jdk 'adoptopenjdk-hotspot-jdk8-latest'
    }
    stages {
        stage('Build') {
            steps {
                sh '''
                    mvn --version
                    mvn clean install
                '''
            }
        }
    }
    post {
        // send a mail on unsuccessful and fixed builds
        unsuccessful { // means unstable || failure || aborted
            emailext subject: 'Build $BUILD_STATUS $PROJECT_NAME #$BUILD_NUMBER!',
            body: '''Check attached console output or $BUILD_URL to view the results.''',
            recipientProviders: [culprits(), requestor()],
            attachLog: true
        }
        fixed { // back to normal
            emailext subject: 'Build $BUILD_STATUS $PROJECT_NAME #$BUILD_NUMBER!',
            body: '''Check $BUILD_URL to view the results.''',
            recipientProviders: [culprits(), requestor()]
        }
    }
}