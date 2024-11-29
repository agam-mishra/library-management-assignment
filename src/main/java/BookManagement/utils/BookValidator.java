package BookManagement.utils;

import BookManagement.models.Book;

public class BookValidator {

    /**
     * Validates the given Book object.
     * 
     * @param book The Book object to validate.
     * @return true if the Book is valid, false otherwise.
     */
    public static boolean validate(Book book) {
        if (book == null) {
            return false;
        }
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            return false;
        }
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            return false;
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            return false;
        }
        if (book.getPublicationYear() <= 0) {
            return false;
        }
        if (book.getTotalCopies() <= 0) {
            return false;
        }
        return true;
    }
}
