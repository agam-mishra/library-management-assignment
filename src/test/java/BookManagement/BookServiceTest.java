package BookManagement;

import BookManagement.models.Book;
import BookManagement.services.BookService;
import BookManagement.services.BookServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setup() {
        bookService = new BookServiceImpl();
        bookService.addBook(new Book("123456", "The Great Gatsby", "F. Scott Fitzgerald", 1925, 3, 3));
        bookService.addBook(new Book("654321", "1984", "George Orwell", 1949, 2, 2));
    }

    @Test
    void testAddBookSuccess() {
        Book newBook = new Book("789012", "Brave New World", "Aldous Huxley", 1932, 5, 5);
        bookService.addBook(newBook);
        Optional<Book> retrievedBook = bookService.findByIsbn("789012");
        assertTrue(retrievedBook.isPresent());
        assertEquals("Brave New World", retrievedBook.get().getTitle());
    }

    @Test
    void testAddBookWithDuplicateISBN() {
        Book duplicateBook = new Book("123456", "Duplicate Book", "Author", 2000, 1, 1);
        bookService.addBook(duplicateBook);
        List<Book> booksByTitle = bookService.searchBooks("Duplicate Book");
        assertTrue(booksByTitle.isEmpty(), "Duplicate book should not be added.");
    }
        
    @Test
    void testUpdateInvalidBook_NotInInventory() {
        Book invalidBook = new Book("000000", "Nonexistent Book", "Author", 2024, 1, 1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.updateBook(invalidBook);
        });
        assertEquals("Book with ISBN 000000 does not exist in the inventory.", exception.getMessage());
    }

    @Test
    void testUpdateInvalidBook_InvalidData() {
        Book invalidBook = new Book(null, "No ISBN Book", "Author", 2024, 1, 1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.updateBook(invalidBook);
        });
        assertEquals("Invalid book data provided for update.", exception.getMessage());
    }

    @Test
    void testFindByIsbnSuccess() {
        Optional<Book> book = bookService.findByIsbn("123456");
        assertTrue(book.isPresent());
        assertEquals("The Great Gatsby", book.get().getTitle());
    }

    @Test
    void testFindByIsbnNotFound() {
        Optional<Book> book = bookService.findByIsbn("999999");
        assertFalse(book.isPresent());
    }

    @Test
    void testSearchBooksByTitle() {
        List<Book> results = bookService.searchBooks("The Great Gatsby");
        assertEquals(1, results.size());
        assertEquals("The Great Gatsby", results.get(0).getTitle());
    }

    @Test
    void testSearchBooksByAuthor() {
        List<Book> results = bookService.searchBooks("George Orwell");
        assertEquals(1, results.size());
        assertEquals("1984", results.get(0).getTitle());
    }

    @Test
    void testSearchBooksNotFound() {
        List<Book> results = bookService.searchBooks("Non-existent Book");
        assertTrue(results.isEmpty());
    }

    @Test
    void testUpdateBookSuccess() {
        Book book = new Book("123456", "The Great Gatsby Updated", "F. Scott Fitzgerald", 1925, 3, 3);
        bookService.updateBook(book);
        Optional<Book> updatedBook = bookService.findByIsbn("123456");
        assertTrue(updatedBook.isPresent());
        assertEquals("The Great Gatsby Updated", updatedBook.get().getTitle());
    }

    @Test
    void testRemoveBookSuccess() {
        bookService.removeBook("123456");
        Optional<Book> book = bookService.findByIsbn("123456");
        assertFalse(book.isPresent(), "Book should be removed successfully.");
    }

    @Test
    void testRemoveBookNotFound() {
        bookService.removeBook("999999"); // No exception should be thrown
        Optional<Book> book = bookService.findByIsbn("999999");
        assertFalse(book.isPresent());
    }
}
