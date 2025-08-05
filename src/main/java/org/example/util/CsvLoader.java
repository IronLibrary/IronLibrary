package org.example.util;

import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Issue;
import org.example.controller.BookController;
import org.example.model.Student;
import org.example.controller.IssueController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    private final BookController bookController;
    private final IssueController issueController;
    public CsvLoader(BookController bookController, IssueController issueController) {
        this.bookController = bookController;
        this.issueController = issueController;
    }

    public List<Book> loadBooksFromCsv(String filePath) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    Book book = new Book(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]));
                    books.add(book);
                }
            }
        }
        catch (IOException e) {
                System.out.println("Error reading CSV: " + e.getMessage());
            }
            return books;
        }

    public List<Author> loadAuthorsFromCsv(String filePath) {
        List<Author> authors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    Book book = bookController.findBookByIsbn(fields[3]);
                    Author author = new Author(Integer.parseInt(fields[0]), fields[1], fields[2], book);
                    authors.add(author);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return authors;
    }

    public List<Student> loadStudentsFromCsv(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    Student student = new Student(fields[0], fields[1]);
                    students.add(student);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return students;
    }

    public List<Issue> loadIssuesFromCsv(String filePath) {
        List<Issue> issues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length >= 1) {
                    Student student = issueController.findStudentByUsn(fields[3]);
                    Book book = bookController.findBookByIsbn(fields[4]);
                    Issue issue = new Issue(Integer.parseInt(fields[0]), fields[1], fields[2], student, book);
                    issues.add(issue);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return issues;
    }
}
