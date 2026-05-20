FROM maven:3.9.6-eclipse-temurin-21-jammy
WORKDIR /automation
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
CMD ["mvn", "clean", "test", "-Dallure.results.directory=/automation/allure-results"]
