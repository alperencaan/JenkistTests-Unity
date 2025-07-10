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
                ws("A:\\Unity6\\JenkistTests-Unity") {  // Burada çalışma dizinini değiştiriyoruz
                    script {
                        bat """
                        "C:\\Program Files\\Unity\\Hub\\Editor\\2022.3.62f1\\Editor\\Unity.exe" -quit -batchmode -projectPath . -executeMethod BuildScript.BuildWindows -logFile -
                        """
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
