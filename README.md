# HR Platform Backend

Бэкенд для приложения "HR Platform" (Rest API)

## Стек технологий

- Java 21
- Spring Boot 4.1.1
- PostgreSQL 18.3
- Flyway 12.4.0
- Swagger (Open API) 3.1.0
- Docker
- JUnit 6.0.3
- Mockito 5.23.0

## Запуск

### Требования

- Java 21+
- Docker
- Среда разработки (Intellij IDEA, VS Code и др.)
- Git
- Postman (рекомендуется для запросов и ручного тестирования)
- DBeaver (рекомендуется для работы с бд)

### Запуск Docker контейнера с БД

Перед запуском приложения необходимо поднять Docker контейнер с PostgreSQL. Для этого в Docker Desktop необходимо открыть командную строку и вставить команду:
```
docker run --name projects_showcase -p 5432:5432 -e POSTGRES_USER={{USERNAME}} -e POSTGRES_PASSWORD={{PASSWORD}} -d postgres
```
В поля USERNAME и PASSWORD необходимо вставить свои значения. Далее в файле application.yaml необходимо подставить ранее введенные значение USERNAME и PASSWORD

## Документация

- **API (Swagger UI):** http://localhost:8080/swagger-ui.html
