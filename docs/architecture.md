# Архитектура папок проекта

## Исходный код
```
ru.moskalenko.hr_platform/
│
├── HrPlatformApplication.java
│
├── web/                             <-- ИНФРАСТРУКТУРА (Глобально для HTTP)
│    ├── config/                     (Security, Swagger, CORS)
│    ├── filter/                     (JwtAuthenticationFilter)
│    └── handler/                    (GlobalExceptionHandler)
│
├── shared/                          <-- ОБЩЕЕ (Бизнес-структуры и утилиты)
│    ├── dto/                        (UserSummary, PageResponse, ErrorResponse)
│    ├── enums/                      (UserRole)
│    ├── exception/                  (BusinessException, NotFoundException)
│    └── utils/                      (DateUtils, StringUtils)
│
├── auth/                            <-- ФИЧА 1
│    ├── controller/
│    ├── service/
│    ├── dto/
│    └── mapper/
│
├── user/                            <-- ФИЧА 2
│    ├── controller/
│    ├── service/
│    ├── repository/
│    ├── entity/
│    ├── dto/
│    └── mapper/
│
├── shift/                           <-- ФИЧА 3
│    ├── controller/
│    ├── service/
│    ├── repository/
│    ├── entity/
│    ├── dto/
│    ├── mapper/
│    └── enums/
│
└── booking/                         <-- ФИЧА 4
     ├── controller/
     ├── service/
     ├── repository/
     ├── entity/
     ├── dto/
     └── mapper/
```

## Тесты
```
hr-platform/ (корень проекта)
├── src/
│   ├── main/
│   │   └── java/ru/moskalenko/hr_platform/
│   │       ├── auth/
│   │       │   ├── AuthController.java
│   │       │   ├── service/AuthService.java
│   │       │   └── service/JwtUtils.java
│   │       └── user/
│   │           ├── UserController.java
│   │           ├── service/UserService.java
│   │           └── repository/UserRepository.java
│   │
│   └── test/  <-- ВСЕ ТЕСТЫ ЗДЕСЬ
│       ├── java/ru/moskalenko/hr_platform/
│       │   │
│       │   ├── auth/
│       │   │   ├── AuthIntegrationTest.java       <-- ИНТЕГРАЦИОННЫЙ тест контроллера (MockMvc)
│       │   │   │                                      (проверяем логин, регистрацию, ошибки 400/401)
│       │   │   │
│       │   │   └── service/
│       │   │       ├── AuthServiceTest.java       <-- UNIT-тест сервиса (Mockito)
│       │   │       │                                      (проверяем логику авторизации, выброс исключений)
│       │   │       └── JwtUtilsTest.java          <-- UNIT-тест утилиты (JUnit)
│       │   │                                              (проверяем генерацию/парсинг токенов)
│       │   │
│       │   ├── user/
│       │   │   ├── UserIntegrationTest.java       <-- ИНТЕГРАЦИОННЫЙ тест (если нужен)
│       │   │   ├── service/
│       │   │   │   └── UserServiceTest.java       <-- UNIT-тест сервиса (Mockito)
│       │   │   └── repository/
│       │   │       └── UserRepositoryTest.java    <-- ТЕСТ РЕПОЗИТОРИЯ (@DataJpaTest)
│       │   │                                              (проверяем, что findByEmail реально работает в БД)
│       │   │
│       │   └── config/
│       │       └── TestSecurityConfig.java        <-- (Опционально) Общие настройки для тестов
│       │
│       └── resources/
│           ├── application-test.yml               <-- Конфиг для тестов (H2 in-memory DB, отключение ненужного)
│           └── data.sql                           <-- (Опционально) SQL-скрипт для тестовых данных
│
└── docs/
    ├── TESTING_GUIDE.md                           <-- Твой гайд по тестированию
    └── TEST_CASES.md                              <-- Таблица с тест-кейсами (как я показывал выше)
```