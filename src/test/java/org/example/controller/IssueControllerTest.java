package org.example.controller;

import org.example.model.Book;
import org.example.model.Issue;
import org.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssueControllerTest {

    private IssueController controller;
    private List<Book> books;
    private List<Student> students;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        books.add(new Book("978-0-00-000000-0", "Java Basics", "Tech", 2));

        students = new ArrayList<>();
        students.add(new Student("S099", "Salva"));

        controller = new IssueController(books, students);
    }

    @Test
    void testLendBookSuccess() {
        String result = controller.lendBook("S099", "Salva", "978-0-00-000000-0");

        assertTrue(result.contains("Book successfully issued"));
        assertEquals(1, controller.getIssues().size());
        assertEquals(1, books.get(0).getQuantity());
    }

    @Test
    void testLendBookNotFound() {
        String result = controller.lendBook("S099", "Salva", "978-1-00-000000-0");

        assertEquals("Book not found!", result);
    }

    @Test
    void testLendBookNoCopiesAvailable() {
        books.get(0).setQuantity(0);
        String result = controller.lendBook("S099", "Salva", "978-0-00-000000-0");

        assertEquals("No copies available!", result);
    }

    @Test
    void testGetIssuesByUsnReturnsCorrectIssue() {
        controller.lendBook("S099", "Salva", "978-0-00-000000-0");

        List<Issue> result = controller.getIssuesByUsn("S099");

        assertEquals(1, result.size(), "Should return one issue");
        assertEquals("Salva", result.get(0).getStudent().getName(), "Student name should match");
        assertEquals("Java Basics", result.get(0).getBook().getTitle(), "Book title should match");
    }

    @Test
    void testFindStudentByUsnSuccess() {
        Student found = controller.findStudentByUsn("S099");
        assertNotNull(found);
        assertEquals("Salva", found.getName());
    }

    @Test
    void testFindStudentByUsnNotFound() {
        Student found = controller.findStudentByUsn("S999");
        assertNull(found);
    }

}
