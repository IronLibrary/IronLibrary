package org.example.controller;

import org.example.view.Menu;
import java.util.Scanner;
import org.example.model.*;
import java.util.List;
import java.util.ArrayList;
import org.example.util.CsvLoader;

public class MenuController {
    /**
     * Constructor de la clase MenuController.
     */

    private static final String BOOK_CSV = "src/main/data/book.csv";
    private static final String AUTHOR_CSV = "src/main/data/author.csv";
    private static final String STUDENT_CSV = "src/main/data/student.csv";
    private static final String ISSUE_CSV = "src/main/data/issue.csv";

    private final IssueController issueController;
    private final BookController bookController;
    List<Book> books;
    private List<Student> students;
    List<Author> authors;
    private CsvLoader loader;
    private List<Issue> issues;

    public MenuController() {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.issues = new ArrayList<>();

        this.issueController = new IssueController(books,students);
        this.bookController = new BookController();
        this.loader = new CsvLoader(this.bookController, this.issueController);
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Updating lists from CSV files...");
            this.students = loader.loadStudentsFromCsv(STUDENT_CSV);
            this.issueController.setStudents(this.students);
            this.books = loader.loadBooksFromCsv(BOOK_CSV);
            this.issues = loader.loadIssuesFromCsv(ISSUE_CSV);
            this.authors = loader.loadAuthorsFromCsv(AUTHOR_CSV);
            this.issueController.setBooks(this.books);
            this.issueController.setIssues(this.issues);

            Menu.showMainMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        this.addBook();
                        break;
                    case 2:
                        this.searchBookByTitle(scanner);
                        break;
                    case 3:
                        this.searchBookByAuthor(scanner);
                        break;
                    case 5:
                        this.listAllBooksAndAuthors();
                        break;
                    case 6:
                        this.lendBookToStudent(scanner);
                        break;
                    case 7:
                        this.listBooksByUSN(scanner);
                        break;
                    case 8:
                        System.out.println(this.students);
                        break;
                    case 9:
                        running = false;
                        System.out.println("Exiting the application...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
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
        try {
            bookController.addBook();
        } catch (Exception e) {
            System.out.println("An error occurred while adding the book: " + e.getMessage());
        }
    }

    private void searchBookByTitle(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        List<Book> book = new ArrayList<>();
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        try {
            book = bookController.searchByTitle(title);
        } catch (Exception e) {
            System.out.println("An error occurred while finding the book: " + e.getMessage());
        }
        if (book.isEmpty()) {
            System.out.println("No books found with the title: " + title);
        } else {
            System.out.println("Books found:");
            for (Book b : book) {
                System.out.println("ISBN: " + b.getIsbn() + ", Title: " + b.getTitle() +
                        ", Category: " + b.getCategory() + ", Quantity: " + b.getQuantity());
            }
        }
    }

    private void searchBookByAuthor(Scanner scanner) {
        System.out.print("Enter book Author to search: ");
        List<Book> book = new ArrayList<>();
        String authorName = scanner.nextLine();
        if (authorName.isEmpty()) {
            System.out.println("Author name cannot be empty.");
            return;
        }
        try {
            book = bookController.searchByAuthor(authorName);
        } catch (Exception e) {
            System.out.println("An error occurred while finding the book: " + e.getMessage());
        }
        if (book.isEmpty()) {
            System.out.println("No books found with the author: " + authorName);
        } else {
            System.out.println("Books found:");
            for (Book b : book) {
                System.out.println("ISBN: " + b.getIsbn() + ", Title: " + b.getTitle() +
                        ", Category: " + b.getCategory() + ", Quantity: " + b.getQuantity());
            }
        }
    }

    void listAllBooksAndAuthors() {
        // This method should list all books and their authors.
        System.out.println("List all books and authors");
        System.out.println("+---------------------------+-------------------+------------+----------+");
        System.out.println("| Title                     | ISBN              | Category   | Quantity |");
        System.out.println("+---------------------------+-------------------+------------+----------+");
        for (Book book : books) {
            System.out.printf("| %-25s | %-17s | %-10s | %8d |\n",
                    book.getTitle(), book.getIsbn(), book.getCategory(), book.getQuantity());
        }
        System.out.println("+---------------------------+-------------------+------------+----------+");

        System.out.println("+---------------------------+---------------------------+");
        System.out.println("| Author                    | Email                     |");
        System.out.println("+---------------------------+---------------------------+");
        for (Author author : authors) {
            System.out.printf("| %-25s | %-25s |\n", author.getName(), author.getEmail());
        }
        System.out.println("+---------------------------+---------------------------+");

    }

}
