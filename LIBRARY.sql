CREATE DATABASE librarydb;
USE librarydb;
CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    publisher VARCHAR(100),
    available BOOLEAN
);
SELECT*FROM books;
UPDATE books
SET available = false
WHERE book_id = 101;
UPDATE books
SET available = true
WHERE book_id = ?;
DELETE FROM books
WHERE book_id = ?;