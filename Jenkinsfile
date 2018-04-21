pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               sh 'pwd'
               sh 'hostname'
               sh 'ls -lrt'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
