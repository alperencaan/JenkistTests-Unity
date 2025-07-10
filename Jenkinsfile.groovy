def PROJECT_NAME = "JenkistTests-Unity"
def CUSTOM_WORKSPACE = "A:\\Unity6"
def UNITY_VERSION = "2022.3.62f1"
def UNITY_INSTALLATION = "C:\\Program Files\\Unity\\Hub\\Editor\\${UNITY_VERSION}\\Editor"

pipeline {
    agent any

    stages {
        stage('Build Windows') {
            when {
                expression { env.BUILD_WINDOWS == 'true' }
            }
            steps {
                script {
                    bat """
                    \"${UNITY_INSTALLATION}\\Unity.exe\" -quit -batchmode -projectPath \"${CUSTOM_WORKSPACE}\\${PROJECT_NAME}\" -executeMethod BuildScript.BuildWindows -logFile -
                    """
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
