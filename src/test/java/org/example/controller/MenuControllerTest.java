package org.example.controller;

import org.example.model.Author;
import org.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuControllerTest {

    private MenuController menuController;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        menuController = new MenuController();

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));

        // Inject test data
        Book book1 = new Book("978-3-16-148410-0", "The Notebook", "Romance", 4);
        Book book2 = new Book("978-3-17-148410-0", "Da Vinci Code", "Mystery", 5);

        Author author1 = new Author("Nicholas Sparks", "nicholassparks@gmail.com", book1);
        Author author2 = new Author("Dan Brown", "danbrown@gmail.com", book2);

        // Manually set the private lists (can use setters or reflection in real apps)
        menuController.books = Arrays.asList(book1, book2);
        menuController.authors = Arrays.asList(author1, author2);
    }

    @Test
    public void testListAllBooksAndAuthors_PrintsCorrectly() {
        menuController.listAllBooksAndAuthors();

        String output = outputStream.toString();

        assertTrue(output.contains("The Notebook"));
        assertTrue(output.contains("Romance"));
        assertTrue(output.contains("Nicholas Sparks"));
        assertTrue(output.contains("Da Vinci Code"));
        assertTrue(output.contains("Mystery"));
        assertTrue(output.contains("Dan Brown"));
    }
}