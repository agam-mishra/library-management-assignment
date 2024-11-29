package BookManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BookManagement.models.Book;
import BookManagement.utils.BookValidator;

class BookValidatorTest {

    @Test
    void testValidateValidBook() {
        Book validBook = new Book("123456", "Clean Code", "Robert C. Martin", 2008, 5, 5);
        assertTrue(BookValidator.validate(validBook), "Expected the book to be valid.");
    }

    @Test
    void testValidateBookWithNullIsbn() {
        Book invalidBook = new Book(null, "Effective Java", "Joshua Bloch", 2008, 3, 3);
        assertFalse(BookValidator.validate(invalidBook), "Expected the book to be invalid due to null ISBN.");
    }

    @Test
    void testValidateBookWithNullTitleAndAuthor() {
        Book invalidBook = new Book("777888", null, null, 2000, 2, 2);
        assertFalse(BookValidator.validate(invalidBook), "Expected the book to be invalid due to null title and author.");
    }
}
