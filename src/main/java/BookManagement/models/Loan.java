package BookManagement.models;

import java.time.LocalDate;

public class Loan {

    private String memberId;
    private String isbn;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    // Constructor to initialize all fields
    public Loan(String memberId, String isbn, LocalDate borrowDate, LocalDate dueDate) {
        this.memberId = memberId;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // Getters and setters
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
               "Member ID='" + memberId + '\'' +
               ", ISBN='" + isbn + '\'' +
               ", Borrow Date=" + borrowDate +
               ", Due Date=" + dueDate +
               '}';
    }
}
