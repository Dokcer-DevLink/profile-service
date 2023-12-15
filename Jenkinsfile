pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub' 
        IMAGE_NAME = 'lordofkangs/profile-service' // DockerHub 저장소 이름
        IMAGE_TAG = 'tagname' // 원하는 태그 이름으로 교체하거나 ${BUILD_NUMBER} 같은 동적 값 사용
        REGISTRY = 'docker.io' // DockerHub 레지스트리
    }

    stages {
        stage('Checkout') {
            steps {
                // Git 저장소 체크아웃
                checkout scm
            }
        }
        stage('Grant Execute Permission') {
            steps {
                sh 'chmod +x gradlew'
            }
        }
        stage('Spring APP Build') {
            steps {
                sh './gradlew bootJar'
            }
        }

        stage('Docker Image Build') {
            steps {
                sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
            }
        }

        stage('Docker Build and Push') {
            steps {
                // DockerHub에 로그인
                withDockerRegistry([ credentialsId: DOCKERHUB_CREDENTIALS, url: "" ]){
                    sh "docker push $IMAGE_NAME:$IMAGE_TAG"
                }
            }
        }
    }
    post {
        always {
            // DockerHub 로그아웃
            sh "docker logout $REGISTRY"
        }
    }
}
