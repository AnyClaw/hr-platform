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