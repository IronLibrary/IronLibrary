package org.example.controller;

import org.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookControllerTest {

    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController();
    }

    @Test
    void testSearchByTitleReturnsCorrectBook() {
        List<Book> results = bookController.searchByTitle("Crime and Punishment");
        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getTitle().toLowerCase().contains("crime and punishment"));
    }

    @Test
    void testSearchByCategoryReturnsCorrectBooks() {
        List<Book> results = bookController.searchByCategory("Classic");
        for (Book book : results) {
            assertEquals("Classic", book.getCategory());
        }
    }

    @Test
    void testGetAllBooksNotEmpty() {
        List<Book> allBooks = bookController.getAllBooks();
        assertNotNull(allBooks);
        assertTrue(allBooks.size() > 0);
    }

    @Test
    void testSearchByAuthorReturnsCorrectBooks() {
        List<Book> results = bookController.searchByAuthor("Stephen King");

        assertNotNull(results);
        assertFalse(results.isEmpty());

        Book book = results.get(0);
        assertEquals("It", book.getTitle());
        assertEquals("Horror", book.getCategory());
    }
}