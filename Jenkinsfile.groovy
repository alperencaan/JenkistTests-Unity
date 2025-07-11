def PROJECT_NAME = "JenkistTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

pipeline {
    agent any

    parameters {
        booleanParam(name: 'BUILD_WINDOWS', defaultValue: true, description: 'Build Windows?')
        booleanParam(name: 'DEPLOY_WINDOWS', defaultValue: false, description: 'Deploy Windows?')
    }

    stages {
        stage('Check Env Variables') {
            steps {
                script {
                    echo "BUILD_WINDOWS = ${params.BUILD_WINDOWS}"
                    echo "DEPLOY_WINDOWS = ${params.DEPLOY_WINDOWS}"
                    echo "UNITY_PATH = ${UNITY_INSTALLATION}\\Unity.exe"
                    echo "PROJECT_PATH = ${CUSTOM_WORKSPACE}\\${PROJECT_NAME}"
                }
            }
        }

        stage('Build & Test') {
            when {
                expression { return params.BUILD_WINDOWS }
            }
            steps {
                bat """
                "${UNITY_INSTALLATION}\\Unity.exe" -runTests -batchmode ^
                -projectPath "${CUSTOM_WORKSPACE}\\${PROJECT_NAME}" ^
                -testResults "C:\\\\temp\\\\results.xml" ^
                -testPlatform editmode ^
                -logFile -
                """
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
