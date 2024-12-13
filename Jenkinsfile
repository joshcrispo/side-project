pipeline {
    agent any

    environment {
        PODMAN_REGISTRY = 'localhost'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Services') {
            parallel {
                stage('Build Weather Service') {
                    steps {
                        dir('weather-service') {
                            sh 'mvn clean package'
                        }
                    }
                }

                stage('Build Location Service') {
                    steps {
                        dir('location-service') {
                            sh 'mvn clean package'
                        }
                    }
                }
            }
        }

        stage('Containerize Services') {
            parallel {
                stage('Containerize Weather Service') {
                    step {
                        dir('weather-service') {
                            sh '''
                                podman build -t weather-service .
                                podman tag location-service ${PODMAN_REGISTRY}/weather-service:latest
                                podman push ${PODMAN_REGISTRY}/weather-service:latest
                            '''
                        }
                    }
                }
                stage('Containerize Location Service') {
                    step {
                        dir('location-service') {
                            sh '''
                                podman build -t location-service .
                                podman tag location-service ${PODMAN_REGISTRY}/location-service:latest
                                podman push ${PODMAN_REGISTRY}/location-service:latest
                            '''
                        }
                    }
                }
            }
        }

        stage('Deploy Services') {
            steps {
                ssh '''
                    podman pod create --name microservices-pod -p 9091:9091 p-9092:9092
                    podman run -d --pod microservices-pod --name location-service ${PODMAN_REGISTRY}/location-service:latest
                    podman run -d --pod microservices-pod --name weather-service ${PODMAN_REGISTRY}/weather-service:latest
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline Execution - DONE!'
        }
    }
}