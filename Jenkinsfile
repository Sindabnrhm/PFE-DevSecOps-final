pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/TON_COMPTE/TON_REPO.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('pfeFinalBack-master') {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('pfeFinal-master') {
                    sh 'npm install --legacy-peer-deps'
                    sh 'npm run build'
                }
            }
        }
    }
}