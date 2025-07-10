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

    parameters {
        booleanParam(name: 'BUILD_WINDOWS', defaultValue: true, description: 'Build Windows?')
        booleanParam(name: 'DEPLOY_WINDOWS', defaultValue: false, description: 'Deploy Windows?')
    }

    stages {
        stage('Check Env Variables') {
            steps {
                echo "BUILD_WINDOWS = ${params.BUILD_WINDOWS}"
                echo "DEPLOY_WINDOWS = ${params.DEPLOY_WINDOWS}"
                echo "UNITY_PATH = ${env.UNITY_PATH}"
                echo "PROJECT_PATH = ${env.PROJECT_PATH}"
            }
        }

        stage('Build Windows') {
            when {
                expression { return params.BUILD_WINDOWS }
            }
            steps {
                dir("${env.PROJECT_PATH}") {
                    bat """
                    "${env.UNITY_PATH}" -quit -batchmode ^
                    -projectPath "${env.PROJECT_PATH}" ^
                    -executeMethod BuildScript.BuildWindows ^
                    -logFile -
                    """
                }
            }
        }

        stage('Deploy Windows') {
            when {
                expression { return params.DEPLOY_WINDOWS }
            }
            steps {
                echo '✅ Deploy Windows stage is running...'
            }
        }
    }
}
