package org.example.model;

import org.example.util.CsvReaderUtil;
import java.io.IOException;

public class Author {
    private int authorId;
    private String name;
    private String email;
    private Book authoredBook;

    //CONSTRUCTOR
    public Author(String name, String email, Book authoredBook) throws IOException {
        this.authorId = CsvReaderUtil.getNextId("src/main/data/author.csv");
        this.name = name;
        this.email = email;
        this.authoredBook = authoredBook;
    }
    public Author(String name, String email) throws IOException {
        this.authorId =  CsvReaderUtil.getNextId("src/main/data/author.csv");
        this.name = name;
        this.email = email;
        this.authoredBook = null; // No book associated initially
    }

    public Author(int id, String name, String email, Book authoredBook) {
        this.authorId =  id;
        this.name = name;
        this.email = email;
        this.authoredBook = authoredBook; // No book associated initially
    }

    //GETTERS


    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Book getAuthoredBook() {
        return authoredBook;
    }

    //SETTERS

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthoredBook(Book authoredBook) {
        this.authoredBook = authoredBook;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", authoredBook=" + authoredBook +
                '}';
    }
}
