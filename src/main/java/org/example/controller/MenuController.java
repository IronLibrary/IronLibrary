package org.example.controller;

import org.example.view.Menu;
import java.util.Scanner;

public class MenuController {
    /**
     * Constructor de la clase MenuController.
     */
    public MenuController() {
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            Menu.showMainMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        this.addBook();
                        break;
                    case 2:
                        this.searchBookByTitle();
                        break;
                    case 3:
                        this.searchBookByAuthor();
                        break;
                    case 5:
                        this.listAllBooksAndAuthors();
                        break;
                    case 6:
                        this.lendBookToStudent();
                        break;
                    case 7:
                        this.listBooksByUSN();
                        break;
                    case 8:
                        running = false;
                        System.out.println("Exiting the application...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        scanner.close();
    }

    private void addBook() {
        System.out.println("Add book (logic here)");
    }

    private void searchBookByTitle() {
        System.out.println("Search book by title (logic here)");
    }

    private void searchBookByAuthor() {
        System.out.println("Search book by author (logic here)");
    }

    private void listAllBooksAndAuthors() {
        System.out.println("List all books and authors (logic here)");
    }

    private void lendBookToStudent() {
        System.out.println("Lend book to student (logic here)");
    }

    private void listBooksByUSN() {
        System.out.println("List books by USN (logic here)");
    }
}
