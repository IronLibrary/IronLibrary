package org.example.model;

import org.example.model.Book;

public class Author {
    private static int idCounter = 1;
    private int authorId;
    private String name;
    private String email;
    private Book authoredBook;

    //CONSTRUCTOR
    public Author(String name, String email, Book authoredBook) {
        this.authorId = idCounter++;
        this.name = name;
        this.email = email;
        this.authoredBook = authoredBook;
    }
    public Author( String name, String email) {
        this.authorId =  idCounter++;
        this.name = name;
        this.email = email;
        this.authoredBook = null; // No book associated initially
    }

    //GETTERS
    public static int getIdCounter() {
        return idCounter;
    }

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
    public static void setIdCounter(int idCounter) {
        Author.idCounter = idCounter;
    }

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
