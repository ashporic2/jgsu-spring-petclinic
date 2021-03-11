pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ashporic2/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {

                // Run Maven on a Unix agent.
                sh "./mvnw clean package"
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
