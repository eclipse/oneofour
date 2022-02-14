pipeline {
    agent any
    tools {
        maven 'apache-maven-3.8.4'
        jdk 'adoptopenjdk-hotspot-jdk8-latest'
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn --batch-mode clean verify"
            }
        }

        stage('Release') {
            when {
                branch pattern: "release-*"
            }
            steps {
                withCredentials([file(credentialsId: 'secret-subkeys.asc', variable: 'KEYRING')]) {
                    sh 'gpg --batch --import "${KEYRING}"'
                    sh 'for fpr in $(gpg --list-keys --with-colons  | awk -F: \'/fpr:/ {print $10}\' | sort -u); do echo -e "5\ny\n" |  gpg --batch --command-fd 0 --expert --edit-key ${fpr} trust; done'
                }

                sh "mvn --batch-mode clean javadoc:jar source:jar deploy -Dchangelist= -DskipTests=true -Pmaven-publish"
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