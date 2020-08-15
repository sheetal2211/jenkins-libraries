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

            stage('Sonar Full Scan'){
                when {
                    anyOf{
                        expression { env.BRANCH_NAME == 'master'}
                    }
                }
                steps{
                    script{
                        echo 'Full analysis'
                    }
                }
            }

            stage('Merge Analysis Scan'){
                when {
                    branch 'PR-*'
                }
                steps{
                    script{
                        echo 'Merge analysis'
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