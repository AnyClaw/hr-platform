-- 1. Генерируем новые смены (создатели - менеджеры с id 3 и 4)
-- 1. Генерируем новые смены (создатели - менеджеры с id 3 и 4)
INSERT INTO shifts (title, description, start_datetime, end_datetime, required_people, created_by, status) VALUES
('Смена в библиотеке', 'Помощь студентам с поиском литературы и сканирование документов',
CURRENT_TIMESTAMP + INTERVAL '4' DAY + INTERVAL '10' HOUR, CURRENT_TIMESTAMP + INTERVAL '4' DAY + INTERVAL '14' HOUR,
3, 3, 'AVAILABLE'),

('Дежурство в деканате', 'Прием документов и консультация студентов по вопросам отчисления',
CURRENT_TIMESTAMP + INTERVAL '3' DAY + INTERVAL '9' HOUR, CURRENT_TIMESTAMP + INTERVAL '3' DAY + INTERVAL '13' HOUR,
2, 4, 'AVAILABLE'),

('Приемная комиссия', 'Работа с абитуриентами, прием заявлений на поступление',
CURRENT_TIMESTAMP + INTERVAL '5' DAY + INTERVAL '10' HOUR, CURRENT_TIMESTAMP + INTERVAL '5' DAY + INTERVAL '15' HOUR,
5, 3, 'AVAILABLE'),

('Смена в IT-отделе', 'Настройка оборудования в компьютерных классах',
CURRENT_TIMESTAMP + INTERVAL '1' DAY + INTERVAL '14' HOUR, CURRENT_TIMESTAMP + INTERVAL '1' DAY + INTERVAL '18' HOUR,
2, 4, 'AVAILABLE'),

('Помощь на конференции', 'Регистрация участников и сопровождение гостей',
CURRENT_TIMESTAMP + INTERVAL '2' DAY + INTERVAL '9' HOUR, CURRENT_TIMESTAMP + INTERVAL '2' DAY + INTERVAL '17' HOUR,
4, 3, 'AVAILABLE');

-- 2. Генерируем тикеты для этих смен
-- Предполагаем, что новые смены получили id с 3 по 7 (так как у тебя уже были id 1 и 2)
INSERT INTO tickets (title, description, status, shift_id) VALUES
-- Тикеты для Смены в библиотеке (shift_id = 3)
('Выдать книги по списку', 'Студентка Иванова просила подобрать литературу по истории', 'ACTIVE', 3),
('Сканирование документов', 'Отсканировать 50 страниц методички для кафедры', 'ACTIVE', 3),

-- Тикеты для Дежурства в деканате (shift_id = 4)
('Принять заявления', 'Собрать заявления на академический отпуск', 'ACTIVE', 4),
('Выдать справки', 'Выдать 10 справок об обучении для военкомата', 'ACTIVE', 4),

-- Тикеты для Приемной комиссии (shift_id = 5)
('Консультация абитуриентов', 'Ответить на вопросы по вступительным экзаменам', 'ACTIVE', 5),
('Проверка документов', 'Сверить оригиналы аттестатов с копиями', 'ACTIVE', 5),
('Внести данные в базу', 'Занести данные новых абитуриентов в 1С', 'ACTIVE', 5),

-- Тикеты для IT-отдела (shift_id = 6)
('Установка ПО', 'Установить Python и IntelliJ IDEA на 15 компьютеров', 'ACTIVE', 6),
('Проверка сети', 'Проверить работу Wi-Fi роутеров на 3 этаже', 'ACTIVE', 6),

-- Тикеты для Конференции (shift_id = 7)
('Регистрация спикеров', 'Выдать бейджи и раздаточные материалы', 'ACTIVE', 7),
('Помощь с проектором', 'Настроить проектор в аудитории 401', 'ACTIVE', 7);