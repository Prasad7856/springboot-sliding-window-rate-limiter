pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "prasaddocker2/rate-limiter"
        DOCKER_TAG = "latest"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Prasad7856/springboot-sliding-window-rate-limiter'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_IMAGE:$DOCKER_TAG
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                ssh -o StrictHostKeyChecking=no ec2-user@EC2_PUBLIC_IP "
                    docker pull $DOCKER_IMAGE:$DOCKER_TAG &&
                    docker stop rate-limiter || true &&
                    docker rm rate-limiter || true &&
                    docker run -d -p 8080:8080 --name rate-limiter $DOCKER_IMAGE:$DOCKER_TAG
                "
                '''
            }
        }
    }
}