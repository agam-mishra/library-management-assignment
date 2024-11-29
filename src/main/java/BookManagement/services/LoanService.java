package BookManagement.services;

import java.util.List;

import BookManagement.dto.LoanResult;
import BookManagement.dto.ReturnResult;
import BookManagement.models.Loan;


/**
 * @author agammishra
 *
 */

public interface LoanService {
    LoanResult borrowBook(String memberId, String isbn);
    ReturnResult returnBook(String memberId, String isbn);
    List<Loan> viewAllLoans();
}
