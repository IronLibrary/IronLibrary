package org.example.model;

public class Book {
    private String isbn;
    private String title;
    private String category;
    private int quantity;
    private Author author;

    //CONSTRUCTOR
    public Book(String isbn, String title, String category, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
    }

    public Book(String isbn, String title, String category, int quantity, Author author) {
        this(isbn, title, category, quantity);
        this.author = author;
    }

    //GETTERS
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public Author getAuthor() { return author; }


    //SETTERS
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAuthor(Author author) { this.author = author; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
