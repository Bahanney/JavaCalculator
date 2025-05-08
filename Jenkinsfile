pipeline {
    agent any
    
    environment {
        DOCKER_HUB_CREDS = credentials('docker-hub-credentials')
        DOCKER_IMAGE = "bahanney/java-calculator"
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Application') {
            steps {
                // Build with Java 8
                sh 'mvn -Dmaven.compiler.source=1.8 -Dmaven.compiler.target=1.8 clean package -DskipTests'
            }
        }
        
        stage('Run Tests') {
            steps {
                sh 'mvn -Dmaven.compiler.source=1.8 -Dmaven.compiler.target=1.8 test'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                sh "echo ${DOCKER_HUB_CREDS_PSW} | docker login -u ${DOCKER_HUB_CREDS_USR} --password-stdin"
                sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                sh "docker push ${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Deploy') {
            steps {
                sh "docker ps -q --filter name=calculator-app | xargs -r docker stop"
                sh "docker ps -a -q --filter name=calculator-app | xargs -r docker rm"
                sh "docker run -d -p 8081:8081 --name calculator-app ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
    }
    
    post {
        always {
            sh "docker logout"
            cleanWs()
        }
    }
}
