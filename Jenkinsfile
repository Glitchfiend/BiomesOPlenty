@Library('forge-shared-library')_

pipeline {
    options {
        disableConcurrentBuilds()
    }
    agent {
        docker {
            image 'gradlewrapper:latest'
            args '-v gradlecache:/gradlecache'
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
                    sh './gradlew ${GRADLE_ARGS} --refresh-dependencies --continue setup'
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
                FORGE_MAVEN_USR = credentials('forge-maven-user')
                FORGE_MAVEN_PSW = credentials('forge-maven-password')
                CURSE_API_KEY = credentials('curse-api-key')
            }
            steps {
                withGradle {
                    sh './gradlew ${GRADLE_ARGS} :uploadArchives curseforge -PforgeMavenUsername=${FORGE_MAVEN_USR} -PforgeMavenPassword=${FORGE_MAVEN_PSW} -PcurseApiKey=${CURSE_API_KEY}'
                }
                
                sh 'curl --user ${FORGE_MAVEN_USR}:${FORGE_MAVEN_PSW} http://files.minecraftforge.net/maven/manage/promote/latest/com.github.glitchfiend.biomesoplenty.BiomesOPlenty/${MYVERSION}'
            }
        }
    }
    post {
        always {
            script {
                archiveArtifacts artifacts: 'build/libs/**/*.*', fingerprint: true, onlyIfSuccessful: true, allowEmptyArchive: true
            }
        }
    }
}