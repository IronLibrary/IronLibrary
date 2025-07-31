package org.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Issue {
    private static int nextId = 1;
    private int id;
    private String issueDate;
    private String returnDate;
    private Student student;
    private Book book;

    public Issue(Student student, Book book) {
        this.id = nextId++;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.issueDate = sdf.format(new Date());
        this.returnDate = calculateReturnDate();
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
}