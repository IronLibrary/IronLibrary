package org.example.controller;

import org.example.model.*;
import org.example.util.CsvWriterUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Book book = findBookByIsbn(isbn);
        if (book == null) return "Book not found!";
        if (book.getQuantity() <= 0) return "No copies available!";

        Student student = findOrCreateStudent(usn, studentName);

        try {
            Issue newIssue = new Issue(student, book);
            issues.add(newIssue);
            String[] issueData = new String[] {
                    String.valueOf(newIssue.getId()),
                    newIssue.getIssueDate(),
                    newIssue.getReturnDate(),
                    student.getUsn(),
                    book.getIsbn()
            };
            CsvWriterUtil.appendLineToCsv(ISSUE_CSV, issueData);
            System.out.println("✅ Issue saved successfully.");

            book.setQuantity(book.getQuantity() - 1);
            return "Book successfully issued! Return date: " + newIssue.getReturnDate();
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
            return "Error issuing book: " + e.getMessage();
        }
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

    public List<Issue> getBooksDueToday() {
        List<Issue> dueToday = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        for (Issue issue : issues) {
            String returnDate = issue.getReturnDate().split("T")[0];
            if (returnDate.equals(today)) {
                dueToday.add(issue);
            }
        }
        return dueToday;
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