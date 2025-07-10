def PROJECT_NAME = "JenkistTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

pipeline {
    agent any

    environment {
        PROJECT_PATH = "${CUSTOM_WORKSPACE}\\${PROJECT_NAME}"
        BUILD_WINDOWS = 'true'       // Buraya true veya false yazabilirsin
        DEPLOY_WINDOWS = 'true'      // Buraya true veya false yazabilirsin
    }

    stages {
        stage('Build Windows') {
            when {
                expression { env.BUILD_WINDOWS == 'true' }
            }
            steps {
                ws("${CUSTOM_WORKSPACE}\\${PROJECT_NAME}") {
                    script {
                        withEnv(["UNITY_PATH=${UNITY_INSTALLATION}"]) {
                            bat '''
                            "%UNITY_PATH%\\Unity.exe" -quit -batchmode -projectPath %PROJECT_PATH% -executeMethod BuildScript.BuildWindows -logFile -
                            '''
                        }
                    }
                }
            }
        }

        stage('Deploy Windows') {
            when {
                expression { env.DEPLOY_WINDOWS == 'true' }
            }
            steps {
                echo 'Deploy Windows stage is running...'
            }
        }
    }
}
