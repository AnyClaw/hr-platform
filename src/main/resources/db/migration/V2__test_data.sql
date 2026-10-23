INSERT INTO users (first_name, last_name, middle_name, birthdate, phone, email, password, role) VALUES
('Иван', 'Петров', 'Сергеевич', '2003-05-15', '+79001234567', 'ivan.petrov@example.com', '$2a$10$hashed_password_1', 'STUDENT'),
('Мария', 'Сидорова', 'Александровна', '2004-08-22', '+79001234568', 'maria.sidorova@example.com', '$2a$10$hashed_password_2', 'STUDENT'),
('Алексей', 'Козлов', 'Дмитриевич', '1985-03-10', '+79001234569', 'alexey.kozlov@example.com', '$2a$10$hashed_password_3', 'MANAGER'),
('Елена', 'Новикова', 'Игоревна', '1990-11-25', '+79001234570', 'elena.novikova@example.com', '$2a$10$hashed_password_4', 'MANAGER');

INSERT INTO student_profiles (user_id, institute, group_name, course) VALUES
(1, 'Институт информационных технологий', 'ИТ-21', 3),
(2, 'Институт компьютерных наук', 'КС-22', 2);

INSERT INTO shifts (title, description, start_datetime, end_datetime, required_people, created_by, status) VALUES
('Утренняя смена поддержки', 'Обработка входящих заявок от пользователей',
 '2026-10-05 09:00:00+03', '2026-10-05 17:00:00+03',
 3, 3, 'AVAILABLE'),
('Вечерняя смена мониторинга', 'Мониторинг систем и обработка инцидентов',
 '2026-10-05 17:00:00+03', '2026-10-06 01:00:00+03',
 2, 4, 'AVAILABLE');

INSERT INTO tickets (title, description, status, shift_id) VALUES
('Настройка VPN доступа', 'Пользователь не может подключиться к корпоративной сети через VPN', 'ACTIVE', 1),
('Проблема с принтером', 'Принтер в кабинете 305 не печатает цветные документы', 'ACTIVE', 1),
('Сбой почтового сервера', 'Не отправляются письма внешним адресатам', 'ACTIVE', 2),
('Обновление ПО', 'Требуется обновление антивируса на рабочих станциях 2 этажа', 'ACTIVE', 2);