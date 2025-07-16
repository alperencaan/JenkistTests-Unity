def PROJECT_NAME = "JenkinsTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

pipeline {
    agent any

    environment {
        PROJECT_PATH = "${CUSTOM_WORKSPACE}\\${PROJECT_NAME}\\JenkistTests-Unity"
        UNITY_PATH = "${UNITY_INSTALLATION}\\Unity.exe"
        EDITMODE_RESULT_PATH = "C:\\temp\\editmode-results.xml"
        PLAYMODE_RESULT_PATH = "C:\\temp\\playmode-results.xml"
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

        stage('Run EditMode Tests') {
            when {
                expression { return params.BUILD_WINDOWS }
            }
            steps {
                bat """
                    cd /d "${env.PROJECT_PATH}"
                    "${env.UNITY_PATH}" -runTests -batchmode -projectPath . -testResults "${env.EDITMODE_RESULT_PATH}" -testPlatform editmode -logFile -
                    dir C:\\temp
                """
            }
        }

        stage('Run PlayMode Tests') {
            when {
                expression { return params.BUILD_WINDOWS }
            }
            steps {
                bat """
                    cd /d "${env.PROJECT_PATH}"
                    "${env.UNITY_PATH}" -runTests -batchmode -projectPath . -testResults "${env.PLAYMODE_RESULT_PATH}" -testPlatform playmode -logFile -
                    dir C:\\temp
                """
            }
        }

        stage('Deploy Windows') {
            when {
                expression { return params.DEPLOY_WINDOWS }
            }
            steps {
                echo '✅ Deploy Windows stage is running...'
                // Deploy komutlarını buraya ekle
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: "${env.EDITMODE_RESULT_PATH}"
            junit allowEmptyResults: true, testResults: "${env.PLAYMODE_RESULT_PATH}"
        }
    }
}
