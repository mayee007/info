pipeline {
    agent { 
		label 'dockerserver'
	}

    parameters { 
        string(name: 'mavenGroupId', defaultValue: 'com.mine', description: '')
        string(name: 'mavenArtifactId', defaultValue: 'info', description: '')
        string(name: 'artifactName', defaultValue: 'info.war', description: '')
        string(name: 'version', defaultValue: '1.0', description: '')
        string(name: 'packaging', defaultValue: 'war', description: '')
        string(name: 'destination', defaultValue: '/root/info/info-api', description: '')
        string(name: 'artifactRepositoryUrl', defaultValue: 'http://localhost:8081/artifactory/snapshots', description: '')
        
    }

    stages {
        stage('Preparation') {
            
            steps {
               git 'https://github.com/mayee007/info.git'
            }
        }
        stage('Build') {
            steps {
               sh 'mvn clean package'
            }
        }
        stage('Upload') {
            steps {
                sh "cp target/*.war target/${params.artifactName}"
                sh "mvn -X deploy:deploy-file -DgroupId=${params.mavenGroupId} -DartifactId=${params.mavenArtifactId} -Dversion=${params.version}-SNAPSHOT -Dpackaging=${params.packaging} -Dfile=target/${params.artifactName} -DrepositoryId=snapshots -Durl=${params.artifactRepositoryUrl}"     
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
