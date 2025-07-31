package org.example.util;

import org.example.model.Book;
import java.util.*;

public class CsvValidator {

    /**
     * Validates a list of Book objects loaded from a CSV file.
     * This method checks for:
     * - Missing or empty fields (ISBN, title, category)
     * - Negative quantity values
     * - Duplicate ISBNs
     *
     * @param books List of books loaded from CSV
     * @return A list of only the valid books
     */
    public List<Book> validateBooks(List<Book> books) {
        // HashSet to keep track of unique ISBNs (to detect duplicates)
        Set<String> seenIsbn = new HashSet<>();

        // List to store valid books
        List<Book> validBooks = new ArrayList<>();

        // Loop through each book and validate
        for (Book book : books) {

            // Check if ISBN is missing or empty
            if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
                System.out.println("❌ Skipping book with missing ISBN: " + book.getTitle());
                continue; // Skip this book
            }

            // Check if Title is missing or empty
            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                System.out.println("❌ Skipping book with missing Title for ISBN: " + book.getIsbn());
                continue;
            }

            // Check if Category is missing or empty
            if (book.getCategory() == null || book.getCategory().isEmpty()) {
                System.out.println("❌ Skipping book with missing Category for ISBN: " + book.getIsbn());
                continue;
            }

            // Check for negative quantity
            if (book.getQuantity() < 0) {
                System.out.println("❌ Skipping book with negative Quantity for ISBN: " + book.getIsbn());
                continue;
            }

            // Check for duplicate ISBNs
            if (!seenIsbn.add(book.getIsbn())) {
                System.out.println("⚠️ Duplicate ISBN found, skipping: " + book.getIsbn());
                continue;
            }

            // If all checks pass, add book to valid list
            validBooks.add(book);
        }

        // Return only valid, clean books
        return validBooks;
    }
}