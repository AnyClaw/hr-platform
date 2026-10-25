# Спецификация API

## Эндпоинты

### POST `/api/auth/login`

Авторизация пользователя (заглушка вместо ЛК МИИТ)

- Request:
```json
{
  "email": "string",
  "password": "string"
}
```
- Response `200` - Успешная авторизация:
```json
{
  "accessToken": "String",
  "refreshToken": "String",
  "expiresIn": "Integer",
  "user": {
    "id": "Integer",
    "role": "String (STUDENT | MANAGER)",
    "firstName": "String",
    "lastName": "String",
    "middleName": "String | Null",
    "email": "String"
  }
}
```
- Response `401` - Ошибка авторизации
- Response `400` - Ошибка валидации

---

### GET `/api/users/me`

Получение информации о текущем авторизованном пользователе

- Headers:
  - `Authorization`: `Bearer <JWT_TOKEN>` (Обязательно)

- Response `200` - Пользователь найден:
```json
{
  "id": "Integer",
  "firstName": "String",
  "lastName": "String",
  "middleName": "String | Null",
  "birthdate": "YYYY-MM-DD",
  "phone": "String",
  "email": "String",
  "role": "String (STUDENT | MANAGER)",
  "studentProfile": {
    "id": "Integer",
    "institute": "String",
    "groupName": "String",
    "course": "Integer"
  }
}
```
- Response `401` - Токен истёк
- Response `400` - Ошибка валидации

---

### GET `/api/users/{id}`

Получение публичной информации о пользователе (доступно всем авторизованным пользователям)

- Response `200` - Пользователь найден
```json
{
  "id": "Integer",
  "firstName": "String",
  "lastName": "String",
  "middleName": "String | Null",
  "role": "String (STUDENT | MANAGER)",
  "studentProfile": {
    "institute": "String",
    "groupName": "String",
    "course": "Integer"
  }
}
```
- Response `404` - Пользователь не найден
- Response `400` - Ошибка валидации

### POST `/api/shifts`

Создание новой смены (только начальник)

- Request:
```json
{
  "title": "String",
  "description": "String | Null",
  "startDatetime": "ISO 8601 Timestamp",
  "endDatetime": "ISO 8601 Timestamp",
  "requiredPeople": "Integer (> 0)",
  "tickets": [
    {
      "title": "String",
      "description": "String | Null"
    }
  ]
}
```
- Response `201` - Смена создана
```json
{
  "id": "Integer",
  "title": "String",
  "description": "String | Null",
  "startDatetime": "ISO 8601 Timestamp",
  "endDatetime": "ISO 8601 Timestamp",
  "requiredPeople": "Integer",
  "status": "String (AVAILABLE)",
  "createdAt": "ISO 8601 Timestamp",
  "createdBy": {
    "id": "Integer",
    "firstName": "String",
    "lastName": "String"
  },
  "tickets": [
    {
      "id": "Integer",
      "title": "String",
      "description": "String | Null",
      "status": "String (ACTIVE)"
    }
  ],
  "bookedCount": 0,
  "availableSlots": "Integer"
}
```
- Response `400` - Ошибка валидации
- Response `403` - Пользователь не является начальником или не авторизован

---

### POST `/api/shifts/{shiftId}/tickets`

Добавление нового тикета к существующей смене (только начальник)

- Request
```json
{
  "title": "String",
  "description": "String | Null"
}
```
- Response `201` - Тикет создан
```json
{
  "id": "Integer",
  "title": "String",
  "description": "String | Null",
  "status": "String (ACTIVE)",
  "shiftId": "Integer",
  "createdAt": "ISO 8601 Timestamp"
}
```
- Response `404` - Смена не найдена
- Response `403` - Пользователь не является начальником или не авторизован
- Response `400` - Ошибка валидации

---

### GET `/api/shifts`

Получение списка доступных смен с фильтрацией

- Headers:
    - `Authorization`: `Bearer <JWT_TOKEN>`

- Query params:
  - `status`: String - фильтр по статусу
  - `startDate`: ISO 8601 Timestamp - фильтр по дате начала
  - `endDate`: ISO 8601 Timestamp - фильтр по дате окончания
  - `availableForMe`: Boolean - показать только доступные для текущего пользователя
  - `page`: Integer - номер страницы (default: 0)
  - `size`: Integer - размер страницы (default: 20)

- Response `200` - Смены получены
```json
{
  "content": [
    {
      "id": "Integer",
      "title": "String",
      "startDatetime": "ISO 8601 Timestamp",
      "endDatetime": "ISO 8601 Timestamp",
      "requiredPeople": "Integer",
      "bookedCount": "Integer",
      "availableSlots": "Integer",
      "status": "String (AVAILABLE | FILLED | IN_WORK)",
      "isAvailableForCurrentUser": "Boolean"
    }
  ],
  "totalElements": "Integer",
  "totalPages": "Integer",
  "currentPage": "Integer",
  "pageSize": "Integer"
}
```
- Response `401` - Не авторизован, возвращается если указан параметр `availableForMe`
- Response `400` - Ошибка валидации

---

### GET `/api/shifts/{shiftId}`

Получение детальной информации о смене

- Response `200` - Успешное получение данных
```json
{
  "id": "Integer",
  "title": "String",
  "description": "String | Null",
  "startDatetime": "ISO 8601 Timestamp",
  "endDatetime": "ISO 8601 Timestamp",
  "requiredPeople": "Integer",
  "status": "String (AVAILABLE | FILLED | IN_WORK | COMPLETED | CANCELED)",
  "createdAt": "ISO 8601 Timestamp",
  "createdBy": {
    "id": "Integer",
    "firstName": "String",
    "lastName": "String"
  },
  "tickets": [
    {
      "id": "Integer",
      "title": "String",
      "description": "String | Null",
      "status": "String (ACTIVE | IN_WORK | COMPLETED | CANCELED)",
      "assignedUsersCount": "Integer"
    }
  ],
  "bookings": [
    {
      "id": "Integer",
      "userId": "Integer",
      "userName": "String",
      "status": "String (BOOKED | IN_PROGRESS | PENDING_APPROVAL | COMPLETED)",
      "bookedAt": "ISO 8601 Timestamp",
      "startedAt": "ISO 8601 Timestamp | Null",
      "finishedAt": "ISO 8601 Timestamp | Null"
    }
  ],
  "bookedCount": "Integer",
  "availableSlots": "Integer",
  "canBook": "Boolean - может ли текущий пользователь забронировать (проверка по availableSlots и отсутствию конфликтов по времени)",
  "totalProgressPercent": "Integer (0-100) - процент выполненных тикетов по смене"
}
```
- Response `404` - Смена не найдена
- Response `400` - Ошибка валидации

---

### POST `/api/shifts/{shiftId}/book`

Бронирование смены студентом

- Headers:
    - `Authorization`: `Bearer <JWT_TOKEN>` (Обязательно)

- Response `201` - Смена забронирована
```json
{
  "id": "Integer",
  "userId": "Integer",
  "shiftId": "Integer",
  "bookedAt": "ISO 8601 Timestamp",
  "status": "String (BOOKED)",
  "message": "Смена успешно забронирована"
}
```
- Response `400` - Нарушение правил бронирования или ошибка валидации
- Response `401` - Не авторизован
- Response `403` - Пользователь не студент
- Response `404` - Смена не найдена
- Response `409` - Смена уже забронирована

### GET `/api/users/me/bookings`

Получение всех бронирований текущего пользователя

- Query params:
  - `status`: String - фильтр по статусу
  - `upcoming`: Boolean - только предстоящие смены
  - `page`: Integer
  - `size`: Integer

- Response `200` - Смены получены
```json
{
  "content": [
    {
      "id": "Integer",
      "shiftId": "Integer",
      "shiftTitle": "String",
      "startDatetime": "ISO 8601 Timestamp",
      "endDatetime": "ISO 8601 Timestamp",
      "status": "String",
      "bookedAt": "ISO 8601 Timestamp",
      "startedAt": "ISO 8601 Timestamp | Null",
      "finishedAt": "ISO 8601 Timestamp | Null",
      "canCancel": "Boolean",
      "canStart": "Boolean",
      "canComplete": "Boolean"
    }
  ],
  "totalElements": "Integer",
  "totalPages": "Integer",
  "currentPage": "Integer"
}
```
- Response `401` - Не авторизован

---

## Общие коды ошибок

- Response `400` - Bad Request
```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Описание ошибки",
  "details": [
    {
      "field": "String",
      "error": "String"
    }
  ],
  "timestamp": "ISO 8601 Timestamp"
}
```

- Response `401` - Unauthorized
```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Требуется авторизация",
  "timestamp": "ISO 8601 Timestamp"
}
```

- Response `403` - Forbidden
```json
{
  "status": 403,
  "error": "Forbidden",
  "message": "Нет прав для выполнения операции",
  "timestamp": "ISO 8601 Timestamp"
}
```

- Response `404` - Not Found
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Ресурс не найден",
  "timestamp": "ISO 8601 Timestamp"
}
```

- Response `409` - Conflict
```json
{
  "status": 409,
  "error": "Conflict",
  "message": "Конфликт данных",
  "timestamp": "ISO 8601 Timestamp"
}
```

- Response `500` - Internal Server Error
```json
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "Внутренняя ошибка сервера",
  "timestamp": "ISO 8601 Timestamp"
}
```