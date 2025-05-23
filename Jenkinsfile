pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDS = credentials('docker-hub-credentials')
        DOCKER_IMAGE = "bahanney/java-calculator-app:${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn -Dmaven.compiler.source=17 -Dmaven.compiler.target=17 clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test -e'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                sh '''
                    echo "$DOCKER_HUB_CREDS_PSW" | docker login -u "$DOCKER_HUB_CREDS_USR" --password-stdin
                    docker push "$DOCKER_IMAGE"
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop calculator-app || echo "Calculator app was not running"
                    docker rm calculator-app || echo "Calculator app was not removed"
                    docker run -d -p 8080:8080 --name calculator-app "$DOCKER_IMAGE"
                '''
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
        }
    }
}
