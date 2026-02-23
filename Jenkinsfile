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
                    sh 'rm -rf node_modules'
                    sh 'npm cache clean --force'
                    sh 'npm config set registry https://registry.npmjs.org/'
                    sh 'npm install --legacy-peer-deps --no-audit --no-fund --progress=false'
                    sh 'npm run build'
                }
            }
        }
    }
}