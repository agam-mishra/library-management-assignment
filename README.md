# Library Book Management Module - Take Home Assignment

## :warning: Please read these instructions carefully and entirely first
* Clone this repository to your local machine (Alternatively, create a private GH fork).
* Use your IDE of choice to complete the assignment.
* There is no time limit for this task - however, for guidance, it is expected to typically take around 1-2 hours.
* Once the assignment is complete, zip it & email it back to the interview panel.
* Alternatively, if you can add the interviewer as a contributor to your private fork, that will suffice too.

## Overview
Create a module that helps manage a library's book inventory and loan system. The system should handle basic operations related to books, members, and the borrowing process.

## Core Requirements

### 1. Book Management
- Add new books to the library with the following details:
  - ISBN (unique identifier)
  - Title
  - Author
  - Publication Year
  - Number of copies
- Remove books from the inventory
- Update book details
- Search for books by ISBN, title, or author

### 2. Loan Management
- Allow members to borrow books
  - Track who has borrowed what
  - Enforce maximum 3 books per member
  - Set due date (14 days from borrowing)
- Process book returns
  - Calculate late fees if applicable (£1 per day late)
  - Update book availability
- View all books currently on loan

## Technical Requirements

### Data Storage
- Use in-memory storage (no database required)
- Organize data structures efficiently
- Ensure thread-safety is considered

### API Design
Design clean interfaces for the following operations:
```java
// Example interfaces (you can modify as needed):
interface BookService {
    void addBook(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> searchBooks(String searchTerm);
    // ... other methods
}

interface LoanService {
    LoanResult borrowBook(String memberId, String isbn);
    ReturnResult returnBook(String memberId, String isbn);
    // ... other methods
}
```

## What We're Looking For
- Clean, readable, and well-organized code
- Clear separation of concerns
- Edge cases consideration
- Unit tests for core functionality
- Clear README with setup instructions
- Any assumptions made

### Tips
* We value simplicity as an architectural virtue and as a development practice. Solutions should reflect the difficulty of the assigned task, and shouldn’t be overly complex. We prefer simple, well tested solutions over clever solutions.
* We value code that communicates well. Code that others can understand, modify, or use is far more valuable than code that is opaque.
* Use the fact that this is a git repo to your advantage, attempt to make your commits meaningful and atomic.
* Use frameworks, libraries, build tools that you are comfortable with as helpers, but the core functionality should be your own, you never know when your users might come up with new problems!

### DO
* ✅ Include unit tests.
* ✅ Test both any client and logic.
* ✅ Update the README.md with any relevant information, assumptions, and/or tradeoffs you would like to highlight.

### DO NOT
* ❌ Submit any form of app, such as web APIs, browser, desktop, or command-line applications.
* ❌ Add unnecessary layers of abstraction.
* ❌ Add unnecessary patterns/ architectural features that aren’t called for e.g. persistent storage.
