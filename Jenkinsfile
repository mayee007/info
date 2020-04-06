pipeline {
    agent none

    parameters { 
        string(name: 'mavenGroupId', defaultValue: 'com.mine', description: '')
        string(name: 'mavenArtifactId', defaultValue: 'info', description: '')
        string(name: 'artifactName', defaultValue: 'info.war', description: '')
        string(name: 'version', defaultValue: '1.1.0', description: '')
        string(name: 'packaging', defaultValue: 'war', description: '')
        string(name: 'destination', defaultValue: '/root/info-scripts/info-api', description: '')
        string(name: 'artifactRepositoryUrl', defaultValue: 'http://localhost:8081/artifactory/snapshots', description: '')
        
    }

    stages {
        stage('Preparation') {
		  parallel {
		    stage('checkout on master') {
				steps {
					git 'https://github.com/mayee007/info.git'
				}
		    }
			stage('checkout on node') {
				agent { node { label 'dockerserver' } }
				steps {
					git 'https://github.com/mayee007/info.git'
				}
		    }
		  }
        }
		stage('SonarQube Analysis') {
            steps {
                sh 'printenv'
                //sh 'mvn clean package -Dmaven.test.skip=true'
				sh 'mvn clean package -e checkstyle:checkstyle -Dspring.profiles.active=dev'
				// sh 'mvn clean package -Dspring.profiles.active=dev'
            }
        }
		
        stage('Build') {
			agent { node { label 'dockerserver' } }
            steps {
                sh 'printenv'
                //sh 'mvn clean package -Dmaven.test.skip=true'
				sh 'mvn clean package -e checkstyle:checkstyle -Dspring.profiles.active=dev'
				// sh 'mvn clean package -Dspring.profiles.active=dev'
            }
        }
        
       
        stage('Upload') {
			agent { node { label 'dockerserver' } }
            steps {
                sh "cp target/*.war target/${params.artifactName}"
                sh "mvn -X deploy:deploy-file -DgroupId=${params.mavenGroupId} -DartifactId=${params.mavenArtifactId} -Dversion=${params.version}-SNAPSHOT -Dpackaging=${params.packaging} -Dfile=target/${params.artifactName} -DrepositoryId=snapshots -Durl=${params.artifactRepositoryUrl} -DuniqueVersion=false"     
            }
        }
        stage('Deploy') {
			agent { node { label 'dockerserver' } }
            steps {
                sh "/root/info-scripts/info-server.sh ${params.destination} ${params.artifactRepositoryUrl} ${params.mavenGroupId} ${params.mavenArtifactId} ${params.version} ${params.packaging} ${params.artifactName} ${params.artifactPath}"
                
            }
        }
    }
}
