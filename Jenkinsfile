pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
           
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
        
                
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/GowthamKandanuru/AutomationTestFramework.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resource/testrunners/testng_regression.xml -Denv=qa"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports of QA Environmentx') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        stage("Deploy to Uat"){
            steps{
                echo("deploy to Uat")
            }
        }
        
        stage('UAT Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/GowthamKandanuru/AutomationTestFramework.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resource/testrunners/testng_regression.xml -Denv=uat"
                    
                }
            }
        }
        
        
        
        stage('Publish Allure Reports of UAT environment') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
    }
}