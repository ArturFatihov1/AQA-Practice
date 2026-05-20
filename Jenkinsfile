pipeline {
    agent any

    tools {
        allure 'allure-cmd'
    }

    stages {
        stage('Clean & Run Tests') {
            steps {
                echo 'Запуск автотестов через Docker Compose...'
                // Нативная команда через пробел, которая поднимет тесты и браузер
                sh 'docker compose up --build --force-recreate --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            echo 'Генерация Allure-отчета...'
            allure includeProperties: false,
                   jdk: '',
                   properties: [],
                   reportBuildPolicy: 'ALWAYS',
                   results: [[path: 'target/allure-results']]

            echo 'Очистка контейнеров...'
            sh 'docker compose down --volumes'
        }
    }
}
