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
                    sh 'npm config set registry https://registry.npmjs.org/'
                    sh 'npm config set fetch-timeout 600000'
                    sh 'npm config set fetch-retries 10'
                    sh 'npm config set network-timeout 600000'
                    sh "export NODE_OPTIONS=--dns-result-order=ipv4first"
                    sh 'npm install --legacy-peer-deps'
                    sh 'npm run build'
                }
            }
        }
    }
}