package org.example.controller;

import org.example.view.Menu;
import java.util.Scanner;
import org.example.model.*;
import java.util.List;
import java.util.ArrayList;

public class MenuController {
    /**
     * Constructor de la clase MenuController.
     */

    private final IssueController issueController;
    private final List<Book> books;
    private final List<Student> students;

    public MenuController() {
        this.bookController = new IssueController();
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();

        //libros para teste

        Author author = new Author("Nicholas Sparks", "nicholas@email.com");
        books.add(new Book("978-3-16-148410-0", "The Notebook", "Romance", 5, author));
        books.add(new Book("978-3-16-148411-0", "A Walk to Remember", "Romance", 3, author));

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

    private void lendBookToStudent(Scanner scanner) {
        System.out.print("Enter student USN: ");
        String usn = scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        String result = issueController.lendBook(usn, name, isbn);
        System.out.println(result);
    }

    private void listBooksByUSN(Scanner scanner) {
        System.out.print("Enter student USN: ");
        String usn = scanner.nextLine();

        List<Issue> issues = issueController.getIssuesByUsn(usn);
        if (issues.isEmpty()) {
            System.out.println("No books issued to this student.");
            return;
        }

        System.out.println("\nBooks issued to student " + usn + ":");
        System.out.println("Title\t\t\tReturn Date");
        for (Issue issue : issues) {
            System.out.printf("%-20s\t%s\n",
                    issue.getBook().getTitle(),
                    issue.getReturnDate());
        }
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
