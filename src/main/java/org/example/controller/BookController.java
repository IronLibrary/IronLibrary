package org.example.controller;

import org.example.model.Book;
import org.example.model.Author;
import org.example.util.CsvWriterUtil;
import org.example.util.PauseUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String BOOK_CSV = "src/main/data/book.csv";
    private static final String AUTHOR_CSV = "src/main/data/author.csv";

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
    private String findAuthorByIsbn(String isbn) {
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
        System.out.print("Enter isbn (e.g 978-x-xx-xxxxxx-x) : ");

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

        System.out.print("Enter title : ");
        String title = scanner.nextLine();

        System.out.print("Enter category : ");
        String category = scanner.nextLine();

        System.out.print("Enter Author name : ");
        String authorName = scanner.nextLine();

        System.out.print("Enter Author email : ");
        String authorEmail = scanner.nextLine();

        System.out.print("Enter number of books : ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Book book = new Book(isbn, title, category, quantity);
        try {
            Author author = new Author(authorName, authorEmail, book);
            author.setAuthoredBook(book);

            String[] bookData = new String[] {
                    isbn, title, category, String.valueOf(quantity)
            };

            String[] authorData = new String[] {
                    String.valueOf(author.getAuthorId()), author.getName(), author.getEmail(), isbn
            };

            CsvWriterUtil.appendLineToCsv(BOOK_CSV, bookData);
            CsvWriterUtil.appendLineToCsv(AUTHOR_CSV, authorData);
            System.out.println("✅ Book and Author saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
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

    public List<Book> searchByTitle(String title) {
        List<Book> allBooks = loadAllBooks();
        return allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByCategory(String category) {
        List<Book> allBooks = loadAllBooks();
        return allBooks.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String authorName) {
        List<Book> allBooks = loadAllBooks();
        String authorIsbn = findAuthorByName(authorName);
        if (authorIsbn == null) {
            System.out.println("❌ Author not found.");
            return new ArrayList<>();
        }
        return allBooks.stream()
                .filter(book -> book.getIsbn().equals(authorIsbn))
                .collect(Collectors.toList());

    }

    private List<Book> loadAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_CSV))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    books.add(new Book(fields[0], fields[1], fields[2], Integer.parseInt(fields[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading books: " + e.getMessage());
        }
        return books;
    }

    private String findAuthorByName(String authorName) {
        try (BufferedReader br = new BufferedReader(new FileReader(AUTHOR_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4 && fields[1].equalsIgnoreCase(authorName)) {
                    return fields[3]; // Return the ISBN associated with the author
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading authors: " + e.getMessage());
        }
        return null;

    }
    public List<Book> getAllBooks() {
        return loadAllBooks(); // this is your private method already working
    }
}