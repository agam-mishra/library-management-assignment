package BookManagement.services;

import java.util.List;
import java.util.Optional;

import BookManagement.models.Book;

/**
 * @author agammishra
 *
 */

public interface BookService {
    void addBook(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> searchBooks(String searchTerm);
    void updateBook(Book book);
    void removeBook(String isbn);
}
