package BookManagement;

import BookManagement.models.Book;
import BookManagement.models.Loan;
import BookManagement.services.BookService;
import BookManagement.services.BookServiceImpl;
import BookManagement.services.LoanService;
import BookManagement.services.LoanServiceImpl;
import BookManagement.dto.LoanResult;
import BookManagement.dto.ReturnResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {
    private BookService bookService;
    private LoanService loanService;

    @BeforeEach
    void setup() {
        bookService = new BookServiceImpl();
        loanService = new LoanServiceImpl(bookService);

        // Add books to store
        bookService.addBook(new Book("123456", "The Great Gatsby", "F. Scott Fitzgerald", 1925, 3, 3));
        bookService.addBook(new Book("654321", "1984", "George Orwell", 1949, 2, 2));
    }

    @Test
    void testBorrowBookSuccess() {
        LoanResult result = loanService.borrowBook("Member001", "123456");
        assertTrue(result.isSuccess());
        assertEquals("Book borrowed successfully.", result.getMessage());
    }

    @Test
    void testBorrowBookNotAvailable() {
        loanService.borrowBook("Member001", "123456");
        loanService.borrowBook("Member002", "123456");
        loanService.borrowBook("Member003", "123456");

        LoanResult result = loanService.borrowBook("Member004", "123456");
        assertFalse(result.isSuccess());
        assertEquals("Book not available.", result.getMessage());
    }

    @Test
    void testBorrowMoreThanThreeBooks() {
        loanService.borrowBook("Member001", "123456");
        loanService.borrowBook("Member001", "654321");
        bookService.addBook(new Book("789012", "Brave New World", "Aldous Huxley", 1932, 1, 1));
        loanService.borrowBook("Member001", "789012");
        
        bookService.addBook(new Book("555555", "Clean Code", "Robert C. Martin", 2008, 5, 5));
        loanService.borrowBook("Member001", "555555");

        LoanResult result = loanService.borrowBook("Member001", "555555");
        assertFalse(result.isSuccess());
        assertEquals("Member has already borrowed the maximum number of books.", result.getMessage());
    }
    
    @Test
    void testBorrowSameBookTwice() {
        LoanResult firstAttempt = loanService.borrowBook("Member001", "123456");
        assertTrue(firstAttempt.isSuccess());
        assertEquals("Book borrowed successfully.", firstAttempt.getMessage());

        LoanResult secondAttempt = loanService.borrowBook("Member001", "123456");
        assertFalse(secondAttempt.isSuccess());
        assertEquals("Member has already borrowed this book.", secondAttempt.getMessage());
    }

    @Test
    void testReturnBookSuccess() {
        loanService.borrowBook("Member001", "123456");
        ReturnResult result = loanService.returnBook("Member001", "123456");

        assertTrue(result.isSuccess());
        assertEquals("Book returned successfully.", result.getMessage());
        assertEquals(0, result.getLateFee());
    }

    @Test
    void testReturnBookLate() {
        loanService.borrowBook("Member001", "123456");

        Loan loan = loanService.viewAllLoans().get(0);
        loan.setDueDate(LocalDate.now().minusDays(3));

        ReturnResult result = loanService.returnBook("Member001", "123456");
        assertTrue(result.isSuccess());
        assertEquals("Book returned successfully.", result.getMessage());
        assertEquals(3, result.getLateFee());
    }

    @Test
    void testReturnBookNotBorrowed() {
        ReturnResult result = loanService.returnBook("Member001", "123456");
        assertFalse(result.isSuccess());
        assertEquals("No loans found for this member.", result.getMessage());
    }

    @Test
    void testViewLoansWithActiveLoans() {
        loanService.borrowBook("Member001", "123456");
        assertEquals(1, loanService.viewAllLoans().size());
    }

    @Test
    void testViewLoansWithNoActiveLoans() {
        assertEquals(0, loanService.viewAllLoans().size());
    }
}
