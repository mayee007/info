pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               sh 'mvn clean package'
            }
        }
        stage('Upload') {
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
