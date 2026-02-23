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
                    sh 'npm config set registry https://registry.npmjs.org/'
                    sh 'npm config set fetch-timeout 600000'
                    sh 'npm config set fetch-retries 5'
                    sh 'npm install --legacy-peer-deps'
                    sh 'npm run build'
                }
            }
        }
    }
}