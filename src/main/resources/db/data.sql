-- Usar la base de datos
USE ironlibrary;

-- Insertar libros
INSERT INTO book (isbn, title, category, quantity) VALUES
('978-0-14-044913-6', 'Crime and Punishment', 'Classic', 5),
('978-1-4028-9467-7', '1984', 'Dystopian', 7),
('978-0-7432-7356-5', 'Angels and Demons', 'Mystery', 3),
('978-0-316-76948-0', 'It', 'Horror', 4),
('978-0-06-112008-4', 'To Kill a Mockingbird', 'Classic', 6),
('978-0-452-28423-4', 'Brave New World', 'Science Fiction', 5),
('978-0-553-21311-7', 'Dune', 'Science Fiction', 2),
('978-0-345-39180-3', 'A Game of Thrones', 'Fantasy', 8),
('978-0-618-00222-8', 'The Hobbit', 'Fantasy', 10),
('978-0-7432-7350-3', 'The Da Vinci Code', 'Mystery', 4);

-- Insertar autores
INSERT INTO author (name, email, book_isbn) VALUES
('Fyodor Dostoevsky', 'dostoevsky@example.com', '978-0-14-044913-6'),
('George Orwell', 'orwell@example.com', '978-1-4028-9467-7'),
('Dan Brown', 'danbrown@example.com', '978-0-7432-7356-5'),
('Stephen King', 'sking@example.com', '978-0-316-76948-0'),
('Harper Lee', 'hlee@example.com', '978-0-06-112008-4'),
('Aldous Huxley', 'huxley@example.com', '978-0-452-28423-4'),
('Frank Herbert', 'fherbert@example.com', '978-0-553-21311-7'),
('George R.R. Martin', 'grrm@example.com', '978-0-345-39180-3'),
('J.R.R. Tolkien', 'tolkien@example.com', '978-0-618-00222-8'),
('Dan Brown', 'dbrown@example.com', '978-0-7432-7350-3');

-- Insertar estudiantes
INSERT INTO student (usn, name) VALUES
('S001', 'Alice Johnson'),
('S002', 'Bob Smith'),
('S003', 'Charlie Davis'),
('S004', 'Diana Evans'),
('S005', 'Ethan Clark'),
('S006', 'Fiona Lewis'),
('S007', 'George Hall'),
('S008', 'Hannah King'),
('S009', 'Ian Moore'),
('S010', 'Julia Roberts');

-- Insertar préstamos (issues)
INSERT INTO issue (issue_date, return_date, student_usn, book_isbn) VALUES
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S001', '978-0-14-044913-6'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S002', '978-1-4028-9467-7'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S003', '978-0-7432-7356-5'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S004', '978-0-316-76948-0'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S005', '978-0-06-112008-4'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S006', '978-0-452-28423-4'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S007', '978-0-553-21311-7'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S008', '978-0-345-39180-3'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S009', '978-0-618-00222-8'),
(NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'S010', '978-0-7432-7350-3');
