package org.example.controller;

import org.example.model.Book;
import org.example.model.Author;
import org.example.util.CsvWriterUtil;
import org.example.util.PauseUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String BOOK_CSV = "src/main/data/book.csv";
    private static final String AUTHOR_CSV = "src/main/data/author.csv";

    public BookController() {
    }

    // Busca un libro por ISBN en el CSV
    public Book findBookByIsbn(String isbn) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4 && fields[0].equals(isbn)) {
                    return new Book(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error leyendo libros: " + e.getMessage());
        }
        return null;
    }
    // Busca un autor por nombre en el CSV
    public String findAuthorByName(String isbn) {
        try (BufferedReader br = new BufferedReader(new FileReader(AUTHOR_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4 && fields[3].equals(isbn)) {
                    return fields[1];
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error leyendo autores: " + e.getMessage());
        }
        return null;
    }

    public void addBook() {
        System.out.print("\t -> Enter isbn (e.g 978-x-xx-xxxxxx-x) : ");

        String isbn = scanner.nextLine();

        Book existingBook = findBookByIsbn(isbn);
        String authorNameFound = findAuthorByName(isbn);

        if (existingBook != null) {
            System.out.println("📚 The book already exists:");
            System.out.println("   • Title: " + existingBook.getTitle());
            System.out.println("   • Category: " + existingBook.getCategory());
            System.out.println("   • Author: " + (authorNameFound != null ? authorNameFound : "Unknown"));
            System.out.println("   • Current quantity: " + existingBook.getQuantity());
            System.out.print("Enter the quantity to add: ");
            int addQuantity = Integer.parseInt(scanner.nextLine());
            existingBook.setQuantity(existingBook.getQuantity() + addQuantity);

            // Actualizar el libro en el CSV
            updateBookInCsv(existingBook);
            System.out.println("Number of books available: " + existingBook.getQuantity());
            System.out.println("✅ Quantity updated successfully.");
            PauseUtil.pause(2000);
            return;
        }

        System.out.print("\t -> Enter title : ");
        String title = scanner.nextLine();

        System.out.print("\t -> Enter category : ");
        String category = scanner.nextLine();

        System.out.print("\t -> Enter Author name : ");
        String authorName = scanner.nextLine();

        System.out.print("\t -> Enter Author email : ");
        String authorEmail = scanner.nextLine();

        System.out.print("\t -> Enter number of books : ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Book book = new Book(isbn, title, category, quantity);
        Author author = new Author(authorName, authorEmail, book);
        author.setAuthoredBook(book);

        String[] bookData = new String[] {
                isbn, title, category, String.valueOf(quantity)
        };

        String[] authorData = new String[] {
                String.valueOf(author.getAuthorId()), author.getName(), author.getEmail(), isbn
        };

        try {
            CsvWriterUtil.appendLineToCsv(BOOK_CSV, bookData);
            CsvWriterUtil.appendLineToCsv(AUTHOR_CSV, authorData);
            System.out.println("✅ Book and Author saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
        System.out.println("📚 Book added successfully:");
        System.out.println("   • Title: " + book.getTitle());
        System.out.println("   • Category: " + book.getCategory());
        System.out.println("   • Author: " + author.getName());
        System.out.println("   • Quantity: " + book.getQuantity());
        System.out.println("   • ISBN: " + book.getIsbn());
        System.out.println("   • Author Email: " + author.getEmail());
        PauseUtil.pause(3000);
    }

    // Actualiza un libro existente en el CSV
    private void updateBookInCsv(Book updatedBook) {
        ArrayList<String[]> allBooks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4 && fields[0].equals(updatedBook.getIsbn())) {
                    allBooks.add(new String[] {
                            updatedBook.getIsbn(),
                            updatedBook.getTitle(),
                            updatedBook.getCategory(),
                            String.valueOf(updatedBook.getQuantity())
                    });
                } else {
                    allBooks.add(fields);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading books to update: " + e.getMessage());
        }
        try {
            CsvWriterUtil.writeLinesToCsv(BOOK_CSV, allBooks);
        } catch (IOException e) {
            System.out.println("❌ Error updating book: " + e.getMessage());
        }
    }
}

