pipeline {
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
