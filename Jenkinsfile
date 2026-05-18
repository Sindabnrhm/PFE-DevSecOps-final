pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
        nodejs 'Node18'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        DOCKER_CREDS = credentials('dockerhub-creds')
    }

    stages {

        // ================= CI BACKEND =================
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Sindabnrhm/PFE-DevSecOps-final.git'
            }
        }
        stage('Build Backend') {
            steps {
                dir('pfeFinalBack-master') {
                    sh 'mvn clean verify'
                }
            }
        }

        stage('SonarQube Scan') {
            steps {
                dir('pfeFinalBack-master') {
                    sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=PFE-DevSecOps-final \
                    -Dsonar.host.url=http://host.docker.internal:9000 \
                    -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                dir('pfeFinalBack-master') {
                    withCredentials([string(credentialsId: 'NVD_API_KEY', variable: 'NVD_API_KEY')]) {
                        sh '''
                        mvn org.owasp:dependency-check-maven:check \
                        -DnvdApiKey=$NVD_API_KEY
                        '''
                    }
                }
            }
        }

        // ================= CI FRONTEND =================

        stage('Install Frontend Dependencies') {
            steps {
                dir('pfeFinal-master') {
                    sh 'npm install --legacy-peer-deps'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('pfeFinal-master') {
                    sh 'npm run build'
                }
            }
        }
        stage('Test Docker') {
            steps {
                sh 'docker version'
            }
        }
        stage('Build Backend Docker Image') {
            steps {
                dir('pfeFinalBack-master') {
                    sh 'docker build -t saidabnrhm/pfe-backend:latest .'
                }
            }
        }

        stage('Push Backend Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh '''
                    echo $PASS | docker login -u $USER --password-stdin
                    docker push saidabnrhm/pfe-backend:latest
                    '''
                }
            }
        }

        stage('Trivy Scan Backend Image') {
            steps {
                sh '''
                echo "=== FULL REPORT ==="
                trivy image \
                --format table \
                --severity LOW,MEDIUM,HIGH,CRITICAL \
                saidabnrhm/pfe-backend:latest
                '''
             }
          }

        stage('Build Frontend Docker Image') {
            steps {
                dir('pfeFinal-master') {
                    sh 'docker build -t saidabnrhm/pfe-frontend:latest .'
                }
            }
        }

        stage('Push Frontend Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh '''
                    echo $PASS | docker login -u $USER --password-stdin
                    docker push saidabnrhm/pfe-frontend:latest
                    '''
                }
            }
        }
        stage('Trivy Scan Frontend Image') {
            steps {
                sh '''
                echo "=== FULL REPORT ==="
                trivy image \
                --format table \
                --severity LOW,MEDIUM,HIGH,CRITICAL \
                saidabnrhm/pfe-frontend:latest
                echo "=== CHECK CRITICAL ==="
                trivy image \
                --timeout 1h \
                --severity CRITICAL \
                --exit-code 1 \
                saidabnrhm/pfe-frontend:latest
                '''
             }
          }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}