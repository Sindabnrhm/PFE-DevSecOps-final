pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                cleanWs()

                retry(3) {
                    timeout(time: 10, unit: 'MINUTES') {
                        sh '''
                        git config --global http.postBuffer 524288000
                        git clone --depth 1 https://github.com/Sindabnrhm/PFE-DevSecOps-final.git .
                        '''
                    }
                }
            }
        }

        stage('Check Files') {
            steps {
                sh 'echo "📂 Vérification des fichiers..."'
                sh 'ls -la'
            }
        }

        // 🔥 IMPORTANT : éviter erreurs Docker (ton problème actuel)
        stage('Clean Docker Environment') {
            steps {
                sh '''
                echo "🧹 Nettoyage Docker..."
                
                docker rm -f mysql-db backend-app frontend-app || true
                docker network rm pfe-network || true
                docker volume rm mysql_data || true
                '''
            }
        }

        stage('Terraform Init') {
            steps {
                dir('terraform') {
                    sh '''
                    echo "⚙️ Initialisation Terraform..."
                    terraform init
                    '''
                }
            }
        }

        stage('Terraform Plan') {
            steps {
                dir('terraform') {
                    sh '''
                    echo "📊 Plan Terraform..."
                    terraform plan
                    '''
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                dir('terraform') {
                    sh '''
                    echo "🚀 Déploiement Terraform..."
                    terraform apply -auto-approve
                    '''
                }
            }
        }

        stage('Wait for Infrastructure') {
            steps {
                sh '''
                echo "⏳ Attente démarrage des containers..."
                sleep 20
                '''
            }
        }

        stage('Ansible Configuration') {
            steps {
                dir('ansible') {
                    sh '''
                    echo "⚙️ Configuration avec Ansible..."
                    ansible-playbook -i inventory.ini playbook.yml
                    '''
                }
            }
        }

        stage('Post Deployment Check') {
            steps {
                sh '''
                echo "🔍 Vérification des containers..."
                docker ps
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline exécuté avec succès !'
        }
        failure {
            echo '❌ Pipeline échoué ! Vérifier les logs.'
        }
    }
}