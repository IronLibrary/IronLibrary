package org.example.util;

import org.example.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    public List<Book> loadBooksFromCsv(String filePath) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    Book book = new Book(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]));
                    books.add(book);
                }
            }
        }
        catch (IOException e) {
                System.out.println("Error reading CSV: " + e.getMessage());
            }
            return books;
        }
    }
