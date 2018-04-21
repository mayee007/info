pipeline {
    agent { 
		label 'dockerserver'
	}

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
