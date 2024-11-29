package BookManagement.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import BookManagement.models.Book;
import BookManagement.utils.BookValidator;

public class BookServiceImpl implements BookService {
    private final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public void addBook(Book book) {
        if (!BookValidator.validate(book)) {
            throw new IllegalArgumentException("Invalid book data provided.");
        }
        books.putIfAbsent(book.getIsbn(), book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public List<Book> searchBooks(String searchTerm) {
        return books.values().stream()
                .filter(book -> book.getTitle().contains(searchTerm) || 
                                book.getAuthor().contains(searchTerm) ||
                                book.getIsbn().contains(searchTerm))
                .collect(Collectors.toList());
    }

    @Override
    public void updateBook(Book book) {
	 if (!BookValidator.validate(book)) {
	        throw new IllegalArgumentException("Invalid book data provided for update.");
	    }

	    if (!books.containsKey(book.getIsbn())) {
	        throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " does not exist in the inventory.");
	    }
    	    
        books.put(book.getIsbn(), book);
    }

    @Override
    public void removeBook(String isbn) {
        books.remove(isbn);
    }

}
