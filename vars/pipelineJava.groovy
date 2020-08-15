def call() {
    pipeline {
        agent any
        options {
            disableConcurrentBuilds()
            buildDiscarder logRotator(numToKeepStr: '10', daysToKeepStr: '2')
        }
        stages {
            stage("GITCLONE"){
                checkout scm
            }

            stage("build"){
                script{
                    sh "./gradlew clean build"
                }
            }
        }
    }
}