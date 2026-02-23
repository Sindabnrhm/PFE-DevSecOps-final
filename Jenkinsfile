pipeline {
    agent any

    stages {

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
                    sh 'rm -rf node_modules package-lock.json'
                    sh 'npm cache clean --force'
                    sh 'npm install --legacy-peer-deps'
                    sh 'npm run build'
                }
            }
        }
    }
}