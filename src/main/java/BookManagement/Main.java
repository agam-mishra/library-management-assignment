package BookManagement;

import java.util.Scanner;
import BookManagement.models.Book;
import BookManagement.services.BookService;
import BookManagement.services.LoanService;
import BookManagement.services.BookServiceImpl;
import BookManagement.services.LoanServiceImpl;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize services
        BookService bookService = new BookServiceImpl();
        LoanService loanService = new LoanServiceImpl(bookService);

        // Preload some books
        bookService.addBook(new Book("123456", "The Great Gatsby", "F. Scott Fitzgerald", 1925, 3, 3));

        while (true) {
            System.out.println("\nOptions: 1. Add Book  2. Borrow Book  3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add a book
                    System.out.println("Enter ISBN:");
                    String isbn = scanner.nextLine();
                    System.out.println("Enter Title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter Author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter Publication Year:");
                    int year = scanner.nextInt();
                    System.out.println("Enter Total Copies:");
                    int copies = scanner.nextInt();

                    bookService.addBook(new Book(isbn, title, author, year, copies, copies));
                    System.out.println("Book added successfully!");
                    break;

                case 2: // Borrow a book
                    System.out.println("Enter Member ID:");
                    String memberId = scanner.nextLine();
                    System.out.println("Enter ISBN of the book to borrow:");
                    String bookIsbn = scanner.nextLine();

                    System.out.println(loanService.borrowBook(memberId, bookIsbn).getMessage());
                    break;

                case 3: // Exit
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
