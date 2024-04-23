# Автоматизация тестирования для OTUS

Репозиторий содержит автоматизированные API тесты, написаны в рамках
курса ["Java QA Engineer. Professional"](https://otus.ru/lessons/java-qa-pro/) для API сервиса [Petstore](https://petstore.swagger.io/), который
предоставляет функциональности для работы с данными о питомцах и заказах в
<<<<<<< HEAD
интернет-магазине. Тесты реализованы с использованием спецификации Swagger API.
=======
интернет-магазине. Тесты реализованы с использованием спецификации `Swagger API`.
>>>>>>> b8dee7f (Api tests)

## Тестовые сценарии

В проекте реализованы следующие автотесты:

- **Поиск заказа по идентификатору**: Тест проверяет корректность получения информации о заказе по его идентификатору.
- **Поиск заказа по неверному идентификатору**: Тест проверяет обработку запроса с неверным идентификатором заказа,
  ожидаемый результат - соответствующее сообщение об ошибке.
- **Проверка возможности добавления питомца в магазин**: Тест проверяет успешное добавление нового питомца в систему
  через API.
- **Проверка возможности добавления питомца в магазин с неверным запросом**: Тест проверяет обработку некорректного
  запроса на добавление питомца, ожидаемый результат - сообщение об ошибке.

## Технологии и инструменты

Автотесты написаны на языке `Java`, организованы и выполняются с помощью фреймворка `JUnit 5`, обеспечивающего удобные
аннотации и функции для управления тестовым процессом и анализа результатов.
Для взаимодействия с API и проверки его откликов используетсябиблиотека `RestAssured`, которая предоставляет мощные
инструменты для тестирования RESTful веб-сервисов.
В проекте используется `Maven` в качестве системы сборки, что обеспечивает управление зависимостями, автоматизацию
сборки и легкую интеграцию с CI/CD пайплайнами. Для автоматического создания необходимых классов на основе спецификации
используется `Swagger`.

<p align="center">
    <a href="https://www.java.com/">
      <img width="8%" title="Java" src="src/main/resources/media/java-original.svg" alt="java">
    </a>
    <a href="https://www.jetbrains.com/">
      <img width="8%" title="IntelliJ IDEA" src="src/main/resources/media/Idea.svg" alt="IntelliJ IDEA">
    </a>
    <a href="https://maven.apache.org/">
      <img width="8%" title="Maven" src="src/main/resources/media/ApacheMaven.svg" alt="Maven">
    </a>
    <a href="https://junit.org/junit5/">
      <img width="8%" title="JUnit5" src="src/main/resources/media/Junit5.svg" alt="JUnit5">
    </a>
    <a href="https://github.com/">
      <img width="8%" title="GitHub" src="src/main/resources/media/github-mark-white.svg" alt="GitHub">
    </a>
    <a href="https://rest-assured.io/">
      <img width="8%" title="Rest Assured" src="src/main/resources/media/rest-assured.png" alt="Rest Assured">
    </a>
    <a href="https://swagger.io/">
      <img width="8%" title="Swagger" src="src/main/resources/media/Swagger.svg" alt="Swagger">
    </a>
</p>

## Команда для запуска автотестов с помощью `Maven` из терминала 
`mvn clean test`

