def PROJECT_NAME = "JenkinsTestUnity"
def CUSTOM_WORKSPACE = "A:\Unity6\JenkinsTests-Unity"

pipeline {
    environment {
        PROJECT_PATH = "${CUSTOM_WORKSPACE}"
    }

    agent {
        label {
            label1 ""
            customWorkspace "${CUSTOM_WORKSPACE}"
        }
    }

    stages {
        stage('Build Windows') {
            when { expression { BUILD_WINDOWS == 'true' } }
            steps {
                // Build işlemleri burada olacak
            }
        }

        stage('Deploy Windows') {
            when { expression { DEPLOY_WINDOWS == 'true' } }
            steps {
                // Deploy işlemleri burada olacak
            }
        }
    }
}
