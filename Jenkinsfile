pipeline {
    agent any

    tools {
        allure 'allure-cmd'
    }

    options {
        timeout(time: 15, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Clean Workspace') {
            steps {
                echo 'Очистка рабочего пространства...'
                cleanWs()
            }
        }

        stage('Run Tests via Docker Compose') {
            steps {
                echo 'Запуск автотестов в Docker...'
                sh 'docker compose up --build --force-recreate --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            echo 'Формирование Allure отчета...'
            allure includeProperties: false,
                   jdk: '',
                   properties: [],
                   reportBuildPolicy: 'ALWAYS',
                   results: [[path: 'target/allure-results']]

            echo 'Очистка Docker-инфраструктуры...'
            sh 'docker compose down --volumes'
        }
    }
}
