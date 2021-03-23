pipeline {
    agent any
    environment {
        PROJECT_VERSION = '1.01'
        ARTIFACTORY_USERNAME = 'admin'
        ARTIFACTORY_PASSWORD = 'password123'
    }
    stages {
        stage ('SNAPSHOT') {
            steps {

                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn clean install -Dmaven.test.failure.ignore=true -Dliquibase.should.run=false sonar:sonar'
                }

                timeout(time: 1, unit: 'HOURS') {
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            mail bcc: '', body: 'Tests in SonarQube are less than 80%', cc: '', from: '', replyTo: '', subject: 'SNAPSHOT FAILED', to: 'uladzislau_biadrytski@epam.com'
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/surefire-reports/*.xml'
                    recordIssues(tools: [checkStyle(), pmdParser(), spotBugs(useRankAsPriority: true)])
                }
                failure {
                    script {
                        mail bcc: '', body: 'Snapshot stage failed.', cc: '', from: '', replyTo: '', subject: 'SNAPSHOT FAILED', to: 'uladzislau_biadrytski@epam.com'
                    }
                }
            }
        }

        stage('RELEASE') {
            steps {
                script {

                    def response = sh(script: 'curl -u ${ARTIFACTORY_USERNAME}:${ARTIFACTORY_PASSWORD}  -s -o /dev/null -w "%{http_code}" ' +
                            'https://artifactory.xfarm.xyz/artifactory/api/storage/libs-release/org/example/web/${PROJECT_VERSION}/web-${PROJECT_VERSION}.war', returnStdout: true)
                    if (response == "200") {
                        currentBuild.result = 'FAILURE'
                        error "release failed"
                    }

                    def artServer = Artifactory.server('ARTIFACTORY_SERVER')
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.tool = 'Maven-3.6'
                    def buildInfo = rtMaven.run pom: 'pom.xml',  goals: 'clean install -Dmaven.test.skip=true -Dliquibase.should.run=false'

                    rtMaven.resolver server: artServer, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                    rtMaven.deployer server: artServer, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'

                    artServer.publishBuildInfo buildInfo
                    rtMaven.deployer.artifactDeploymentPatterns.addInclude("*.war")
                    rtMaven.deployer.deployArtifacts buildInfo
                }
            }
            post {
                failure {
                    script {
                        mail bcc: '', body: 'Release stage failed.', cc: '', from: '', replyTo: '', subject: 'RELEASE FAILED', to: 'uladzislau_biadrytski@epam.com'
                    }
                }
            }
        }

        stage('DEPLOY') {
            steps {
                script {
                    sh 'mvn clean install -Dmaven.test.skip=true -Dliquibase.should.run=false'

                    sh 'mv web/target/web-${PROJECT_VERSION}.war web/target/tic-tac-toe.war'

                    deploy adapters: [tomcat8(credentialsId: 'cd34afab-d0bd-4e08-949e-d2f2ebf703ef', path: '', url: 'http://tomcat:8080')], contextPath: null, war: 'web/target/*.war'

                    def statusWebApp = sh(script: 'curl -s -o /dev/null -w "%{http_code}" http://35.239.53.104:8087/tic-tac-toe/', returnStdout: true)
                    if (statusWebApp != "302") {
                        currentBuild.result = 'FAILURE'
                        error "deploy failed"
                    }
                }
            }
            post {
                failure {
                    script {
                        mail bcc: '', body: 'Deploy stage failed.', cc: '', from: '', replyTo: '', subject: 'DEPLOY FAILED', to: 'uladzislau_biadrytski@epam.com'
                    }
                }
            }
        }
    }
}