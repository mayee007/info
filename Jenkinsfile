pipeline {
    agent "dockerserver"

    stages {
        stage('Build') {
            steps {
               sh 'pwd'
               sh 'hostname'
               sh 'ls -lrt'
			   sh 'mvn clean package'
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
