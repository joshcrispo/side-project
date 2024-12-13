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
                    steps {
                        dir('weather-service') {
                            sh '''
                                podman build -t weather-service .
                            '''
                        }
                    }
                }
                stage('Containerize Location Service') {
                    steps {
                        dir('location-service') {
                            sh '''
                                podman build -t location-service .
                            '''
                        }
                    }
                }
            }
        }

        stage('Deploy Services') {
            steps {
                sh '''
                    podman pod create --name microservices-pod -p 9091:9091 p-9092:9092
                    podman run -d --pod microservices-pod --name location-service location-service:latest
                    podman run -d --pod microservices-pod --name weather-service weather-service:latest
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