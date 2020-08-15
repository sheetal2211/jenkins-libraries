def call() {
    pipeline {
        agent any
        options {
            disableConcurrentBuilds()
            buildDiscarder logRotator(numToKeepStr: '10', daysToKeepStr: '2')
        }
        stages {
            stage("build"){
                steps {
                    script{
                        sh "./gradlew clean build"
                    }
                }
            }
        }
        post {
            always {
                script {
                    deleteDir()
                }
            }
        }
    }
}