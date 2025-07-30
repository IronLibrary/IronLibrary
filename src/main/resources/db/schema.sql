-- 1. Create the database
CREATE DATABASE IF NOT EXISTS ironlibrary;
USE ironlibrary;

-- 2. Books table
CREATE TABLE IF NOT EXISTS book (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    quantity INT NOT NULL
);

-- 3. Authors table
CREATE TABLE IF NOT EXISTS author (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    book_isbn VARCHAR(20) UNIQUE, -- one-to-one relationship with book
    FOREIGN KEY (book_isbn) REFERENCES book(isbn)
);

-- 4. Students table
CREATE TABLE IF NOT EXISTS student (
    usn VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- 5. Issues table (loans)
CREATE TABLE IF NOT EXISTS issue (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    issue_date DATETIME NOT NULL,
    return_date DATETIME NOT NULL,
    student_usn VARCHAR(20) UNIQUE,
    book_isbn VARCHAR(20) UNIQUE,
    FOREIGN KEY (student_usn) REFERENCES student(usn),
    FOREIGN KEY (book_isbn) REFERENCES book(isbn)
);