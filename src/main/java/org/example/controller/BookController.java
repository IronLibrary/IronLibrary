package org.example.controller;

import org.example.model.Book;
import org.example.model.Author;
import org.example.util.CsvWriterUtil;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BookController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String BOOK_CSV = "src/main/data/book.csv";
    private static final String AUTHOR_CSV = "src/main/data/author.csv";

    // Busca un libro por ISBN en el CSV
    private Book findBookByIsbn(String isbn) {
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

    public void addBook() {
        System.out.print("Enter isbn : ");
        String isbn = scanner.nextLine();

        Book existingBook = findBookByIsbn(isbn);

        if (existingBook != null) {
            System.out.println("El libro ya existe. Título: " + existingBook.getTitle());
            System.out.println("Categoría: " + existingBook.getCategory());
            System.out.println("Cantidad actual: " + existingBook.getQuantity());
            System.out.print("Ingrese la cantidad a añadir: ");
            int addQuantity = Integer.parseInt(scanner.nextLine());
            existingBook.setQuantity(existingBook.getQuantity() + addQuantity);

            // Actualizar el libro en el CSV
            updateBookInCsv(existingBook);
            System.out.println("Numeros de libros disponibles: " + existingBook.getQuantity());
            System.out.println("✅ Cantidad actualizada correctamente.");
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
    }

    // Actualiza un libro existente en el CSV
    private void updateBookInCsv(Book updatedBook) {
        List<String[]> allBooks = new ArrayList<>();
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
            System.out.println("❌ Error leyendo libros para actualizar: " + e.getMessage());
        }
        try {
            CsvWriterUtil.writeLinesToCsv(BOOK_CSV, allBooks);
        } catch (IOException e) {
            System.out.println("❌ Error actualizando libro: " + e.getMessage());
        }
    }
}

