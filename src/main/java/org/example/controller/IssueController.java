// src/main/java/org/example/controller/IssueController.java
package org.example.controller;

import org.example.model.*;
import org.example.util.CsvWriterUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IssueController {
    private List<Issue> issues;
    private List<Book> books;
    private List<Student> students;
    private static final String ISSUE_CSV = "src/main/data/issue.csv";

    public IssueController(List<Book> books, List<Student> students) {
        this.issues = new ArrayList<>();
        this.books = books;
        this.students = students;
    }

    public IssueController() {
    }

    public String lendBook(String usn, String studentName, String isbn) {
        // 1. Find the book
        Book book = findBookByIsbn(isbn);
        if (book == null) return "Book not found!";

        // 2. Check availability
        if (book.getQuantity() <= 0) return "No copies available!";

        // 3. Find or create student
        Student student = findOrCreateStudent(usn, studentName);

        // 4. Create issue record
        Issue newIssue = new Issue(student, book);
        issues.add(newIssue);
        String[] issueData = new String[] {
                String.valueOf(newIssue.getId()), newIssue.getIssueDate(),newIssue.getReturnDate(), student.getUsn(),
                book.getIsbn()
        };
        try {
            CsvWriterUtil.appendLineToCsv(ISSUE_CSV, issueData);
            System.out.println("✅ Issue saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }


        // 5. Update stock
        book.setQuantity(book.getQuantity() - 1);

        return "Book successfully issued! Return date: " + newIssue.getReturnDate();
    }

    public List<Issue> getIssuesByUsn(String usn) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            Student student = issue.getStudent();
            if (student != null && student.getUsn().equals(usn)) {
                result.add(issue);
            }
        }
        return result;
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private Student findOrCreateStudent(String usn, String name) {
        for (Student student : students) {
            if (student.getUsn().equals(usn)) {
                return student;
            }
        }
        Student newStudent = new Student(usn, name);
        students.add(newStudent);
        return newStudent;
    }

    public Student findStudentByUsn(String usn) {
        for (Student student : students) {
            System.out.println("Checking student: " + student + " for USN: " + usn);
            if (student.getUsn().equals(usn)) {
                return student;
            }
        }
        return null;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}