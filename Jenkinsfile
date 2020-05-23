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
        stage('checkout all code') {
          parallel {
          stage('checkout all code') {
          stages {
		    stage('checkoutonmaster') {
		        agent { node { label 'master' } }
				steps {
					git 'https://github.com/mayee007/info.git'
				}
		    }
			stage('checkoutonnode') {
				agent { node { label 'fullHouse' } }
				steps {
					git 'https://github.com/mayee007/info.git'
				}
		    }
          } 
              
          } // stages 
          } //parallel 
        } // stage 'checkout all nodes'
        
        stage('SonarQube analysis') {
			agent { node { label 'master' } }
            steps {
                bat 'sonar-scanner -Dsonar.projectKey=Info -Dsonar.java.binaries=src'
            }
        }
	
        stage('Build') {
			agent { node { label 'fullHouse' } }
            steps {
                sh 'printenv'
                //sh 'mvn clean package -Dmaven.test.skip=true'
				sh 'mvn clean package -e checkstyle:checkstyle -Dspring.profiles.active=dev'
				// sh 'mvn clean package -Dspring.profiles.active=dev'
            }
        }
        
       
        /* stage('Upload') {
			agent { node { label 'dockerserver' } }
            steps {
                sh "cp target/*.war target/${params.artifactName}"
                sh "mvn -X deploy:deploy-file -DgroupId=${params.mavenGroupId} -DartifactId=${params.mavenArtifactId} -Dversion=${params.version}-SNAPSHOT -Dpackaging=${params.packaging} -Dfile=target/${params.artifactName} -DrepositoryId=snapshots -Durl=${params.artifactRepositoryUrl} -DuniqueVersion=false"     
            }
        } */ 
        stage('Deploy') {
			agent { node { label 'dockerserver' } }
            steps {
                //sh "/root/info-scripts/info-server.sh ${params.destination} ${params.artifactRepositoryUrl} ${params.mavenGroupId} ${params.mavenArtifactId} ${params.version} ${params.packaging} ${params.artifactName} ${params.artifactPath}"
                sh "ls -lrt target"
            }
        }
    }
    }
