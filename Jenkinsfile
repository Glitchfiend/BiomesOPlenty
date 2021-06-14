@Library('forge-shared-library')_

pipeline {
    options {
        disableConcurrentBuilds()
    }
    agent {
        docker {
            image 'gradle:jdk8'
            args '-v forgegc:/home/gradle/.gradle/'
        }
    }
    environment {
        GRADLE_ARGS = '--no-daemon --console=plain' // No daemon for now as FG3 kinda derps. //'-Dorg.gradle.daemon.idletimeout=5000'
        JENKINS_HEAD = 'https://wiki.jenkins-ci.org/download/attachments/2916393/headshot.png'
    }

    stages {
        stage('fetch') {
            steps {
                checkout scm
            }
        }
        stage('setup') {
            steps {
                withGradle {
                    sh './gradlew ${GRADLE_ARGS} --refresh-dependencies'
                }
                script {
                    env.MYVERSION = sh(returnStdout: true, script: './gradlew :properties -q | grep "^version:" | awk \'{print $2}\'').trim()
                }
            }
        }
        stage('changelog') {
            when {
                not {
                    changeRequest()
                }
            }
            steps {
                writeChangelog(currentBuild, "build/BiomesOPlenty-${env.MYVERSION}-changelog.txt")
            }
        }
        stage('publish') {
            when {
                not {
                    changeRequest()
                }
            }
            environment {
                CURSE_API_KEY = credentials('curse-api-key')
            }
            steps {
                withGradle {
                    sh './gradlew ${GRADLE_ARGS} :uploadArchives curseforge -PcurseApiKey=${CURSE_API_KEY}'
                }
            }
        }
    }
}