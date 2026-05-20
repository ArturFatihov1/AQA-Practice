pipeline {
    agent any

    tools {
        allure 'allure-cmd'
    }

    stages {
        stage('Run AQA Tests') {
            // Jenkins сам поднимет этот контейнер, прогонит внутри команду и закроет его
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21-jammy'
                    // Пробрасываем аргументы: сеть хоста, чтобы видеть запущенный отдельно браузер
                    args '--network host'
                }
            }
            steps {
                echo 'Запуск тестов внутри чистого Maven-контейнера...'
                // Запускаем тесты. Allure-результаты автоматически окажутся в target/allure-results
                sh 'mvn clean test'
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
        }
    }
}
