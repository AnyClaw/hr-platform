# HR Platform Backend

Бэкенд для приложения "HR Platform" (Rest API)

## Стек технологий

- Java 21
- Spring Boot 4.1.1
- PostgreSQL 18.3
- Flyway 12.4.0
- Swagger (Open API) 3.1.0
- Docker / Docker Compose
- JUnit 6.0.3
- Mockito 5.23.0

## Запуск

### Требования

- Java 21+
- Docker и Docker Compose
- Среда разработки (Intellij IDEA, VS Code и др.)
- Git
- Postman (рекомендуется для запросов и ручного тестирования)
- DBeaver (рекомендуется для работы с бд)

### Пошаговая инструкция

1. **Клонировать репозиторий**
    ```bash
    git clone <ссылка_на_репозиторий>
    cd hr-platform
    ```
2. **Настроить переменные окружения**

    В корне проекта лежит файл `.env.example`. Необходимо скопировать его в .env и заменить
    значение переменных на свои
3. **Запустить базу данных**
    
    Необходимо убедиться, что Docker Desktop запущен, после чего выполнить в терминале 
    из корня проекта команду:
    ```bash
    docker compose up -d --wait postgres
    ```
4. **Запустить приложение**

   Открыть проект в IDE и запустить класс HrPlatformApplication (или использовать
   ./mvnw spring-boot:run). Flyway автоматически накатит все миграции и создаст таблицы 
   при первом запуске.

## Документация

- **API (Swagger UI):** http://localhost:8080/swagger-ui.html
