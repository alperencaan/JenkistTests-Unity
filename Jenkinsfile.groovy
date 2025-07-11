def PROJECT_NAME = "JenkinsTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

pipeline {
    agent any

    environment {
        UNITY_PATH = "C:\\Program Files\\Unity\\Hub\\Editor\\2022.3.62f1\\Editor\\Unity.exe"
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
                echo "PROJECT_PATH = A:\\Unity6\\JenkinsTests-Unity\\JenkistTests-Unity"
            }
        }

        stage('Build & Test') {
            when {
                expression { return params.BUILD_WINDOWS }
            }
            steps {
                bat """
                cd /d "A:\\Unity6\\JenkinsTests-Unity\\JenkistTests-Unity" &&
                "${env.UNITY_PATH}" -runTests -batchmode ^
                -projectPath . ^
                -testResults "C:\\temp\\results.xml" ^
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
