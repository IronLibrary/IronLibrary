package org.example.model;

import org.example.util.CsvReaderUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Issue {
    private final int id;
    private final String issueDate;
    private final String returnDate;
    private final Student student;
    private final Book book;

    public Issue(Student student, Book book) throws IOException {
        this.id = CsvReaderUtil.getNextId("src/main/data/issue.csv");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.issueDate = sdf.format(new Date());
        this.returnDate = calculateReturnDate();
        this.student = student;
        this.book = book;
    }

    public Issue(int id, String issueDate, String returnDate, Student student, Book book) {
        this.id = id;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.student = student;
        this.book = book;
    }

    private String calculateReturnDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(issueDate);
            long timeInMillis = date.getTime() + (7 * 24 * 60 * 60 * 1000); // 7 days
            return sdf.format(new Date(timeInMillis));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calculating date";
        }
    }

    // Getters
    public int getId() { return id; }
    public String getIssueDate() { return issueDate; }
    public String getReturnDate() { return returnDate; }
    public Student getStudent() { return student; }
    public Book getBook() { return book; }

    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", student=" + student +
                ", book=" + book +
                '}';
    }
}