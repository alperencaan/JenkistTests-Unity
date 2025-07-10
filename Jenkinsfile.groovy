def PROJECT_NAME = "JenkistTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

pipeline {
    agent any

    environment {
        PROJECT_PATH = "${CUSTOM_WORKSPACE}\\${PROJECT_NAME}"
        UNITY_PATH = "${UNITY_INSTALLATION}\\Unity.exe"
    }

    stages {
        stage('Check Env Variables') {
            steps {
                echo "BUILD_WINDOWS = ${env.BUILD_WINDOWS}"
                echo "DEPLOY_WINDOWS = ${env.DEPLOY_WINDOWS}"
            }
        }

        stage('Build Windows') {
            when {
                expression { env.BUILD_WINDOWS == 'true' }
            }
            steps {
                ws("${env.PROJECT_PATH}") {
                    bat """
                    "${env.UNITY_PATH}" -quit -batchmode -projectPath . -executeMethod BuildScript.BuildWindows -logFile -
                    """
                }
            }
        }

        stage('Deploy Windows') {
            when {
                expression { env.DEPLOY_WINDOWS == 'true' }
            }
            steps {
                echo '✅ Deploy Windows stage is running...'
            }
        }
    }
}
