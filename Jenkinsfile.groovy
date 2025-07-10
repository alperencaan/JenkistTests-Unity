def PROJECT_NAME = "JenkistTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

ppipeline {
    agent any

    parameters {
        booleanParam(name: 'BUILD_WINDOWS', defaultValue: true, description: 'Run build stage?')
        booleanParam(name: 'DEPLOY_WINDOWS', defaultValue: true, description: 'Run deploy stage?')
    }

    environment {
        PROJECT_PATH = "${CUSTOM_WORKSPACE}\\${PROJECT_NAME}"
    }

    stages {
        stage('Build Windows') {
            when {
                expression { params.BUILD_WINDOWS }
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
                expression { params.DEPLOY_WINDOWS }
            }
            steps {
                echo 'Deploy Windows stage is running...'
            }
        }
    } 
}

