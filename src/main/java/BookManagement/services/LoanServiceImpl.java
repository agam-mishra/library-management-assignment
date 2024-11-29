package BookManagement.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import BookManagement.dto.LoanResult;
import BookManagement.dto.ReturnResult;
import BookManagement.models.Book;
import BookManagement.models.Loan;

public class LoanServiceImpl implements LoanService {
	// Using Hashmap here for better time complexity when storing data locally in application.
	// When storing data in database, using List will represent data in better way. 
    private final Map<String, List<Loan>> memberLoans = new ConcurrentHashMap<>();
    private final BookService bookService;

    public LoanServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public LoanResult borrowBook(String memberId, String isbn) {
        if (memberId == null || memberId.isEmpty() || isbn == null || isbn.isEmpty()) {
            return new LoanResult(false, "Invalid memberId or ISBN.");
        }

        Book book = bookService.findByIsbn(isbn).orElse(null);
        if (book == null || book.getAvailableCopies() == 0) {
            return new LoanResult(false, "Book not available.");
        }

        List<Loan> loans = memberLoans.getOrDefault(memberId, new ArrayList<>());
        boolean alreadyBorrowed = loans.stream()
                .anyMatch(loan -> loan.getIsbn().equals(isbn));
        
			if (alreadyBorrowed) {
			return new LoanResult(false, "Member has already borrowed this book.");
			}

        if (loans.size() >= 3) {
            return new LoanResult(false, "Member has already borrowed the maximum number of books.");
        }

        synchronized (book) {
            if (book.getAvailableCopies() == 0) {
                return new LoanResult(false, "Book not available."); // Double-check availability
            }
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }

        Loan loan = new Loan(memberId, isbn, LocalDate.now(), LocalDate.now().plusDays(14));
        loans.add(loan);
        memberLoans.put(memberId, loans);

        return new LoanResult(true, "Book borrowed successfully.");
    }

    @Override
    public ReturnResult returnBook(String memberId, String isbn) {
        if (memberId == null || memberId.isEmpty() || isbn == null || isbn.isEmpty()) {
            return new ReturnResult(false, "Invalid memberId or ISBN.", 0);
        }

        List<Loan> loans = memberLoans.get(memberId);
        if (loans == null || loans.isEmpty()) {
            return new ReturnResult(false, "No loans found for this member.", 0);
        }

        Loan loan = loans.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (loan == null) {
            return new ReturnResult(false, "Book not borrowed by this member.", 0);
        }

        loans.remove(loan);

        Book book = bookService.findByIsbn(isbn).orElse(null);
        if (book != null) {
            synchronized (book) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
            }
        }

        long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
        int lateFee = daysLate > 0 ? (int) daysLate : 0;

        return new ReturnResult(true, "Book returned successfully.", lateFee);
    }

    @Override
    public List<Loan> viewAllLoans() {
        return memberLoans.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
