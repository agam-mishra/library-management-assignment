# **Library Management System (In-Memory Java Application)**

This project is a simple in-memory library management system built in Java that provides core functionality for managing books and loans. The system is designed to allow users to add, update, borrow, and return books, all stored in memory (no external database is required).

---

## **Features**

### **1. Book Management**
- Add new books with details like ISBN, title, author, and total copies.
- Search for books by ISBN, title, or author.
- Update book details (title, author, total copies).
- Remove books from the library inventory.

### **2. Loan Management**
- Borrow books (maximum of 3 books per member).
- Enforce a 14-day loan period.
- Calculate late fees (£1 per day after the due date).
- Return books and update availability.
- View all current loans.

---

## **Setup Instructions (Using Eclipse)**

### **Prerequisites**
- **Java Development Kit (JDK)** version 8 or higher.
- **Eclipse IDE** (or any compatible Java IDE).
- **JUnit 5** (included in Eclipse, no additional setup needed).

---

### **Project Setup in Eclipse**

1. **Clone or Download the Project**
   - Clone the repository or download the source code as a zip file.
   - Extract the zip file and open Eclipse.

2. **Import Project into Eclipse**
   - In Eclipse, go to `File` → `Open Projects from File System`.
   - Choose the directory where the project is located and click **Finish**.

3. **Verify Project Structure**
   - Ensure that `src/main/java` and `src/test/java` are recognized as source folders in Eclipse:
     - Right-click the project → `Build Path` → `Configure Build Path`.
     - Check that `src/main/java` and `src/test/java` are under **Source**.

4. **Build the Project**
   - Eclipse should automatically build the project. If not, enable **Build Automatically**:
     - Go to `Project` → Ensure **Build Automatically** is checked.
   - If needed, clean the project:
     - Go to `Project` → `Clean` → Select the project and click **OK**.

5. **Run the Application**
   - In the `src/main/java/BookManagement` package, locate the `Main.java` file.
   - Right-click on `Main.java` → `Run As` → `Java Application`.

---

### **Running Unit Tests (Using Eclipse)**

1. Navigate to the `src/test/java/BookManagement` package in Eclipse.
2. Right-click on the `LoanServiceTest` or `BookServiceTest` class.
3. Select `Run As` → `JUnit Test`.
4. View the test results in the **JUnit** tab.

---

## **Directory Structure**
<img width="696" alt="image" src="https://github.com/user-attachments/assets/f0067ae6-612c-4edf-acec-7a3c38a5a4d7">


---

## **Assumptions Made**
1. **In-Memory Storage**: All data is stored in memory, meaning the data will be lost when the application is restarted.
2. **Fixed Late Fee**: A late fee of £1 per day is applied if the book is returned after the 14-day borrowing period.
3. **Single-Threaded**: Thread-safety is considered using `ConcurrentHashMap`, assuming a single-user environment.
4. **Unique ISBNs**: Each book has a unique ISBN, and books can be identified and manipulated using their ISBNs.
5. **Simple Output**: The system's actions are shown in the console, validated primarily by unit tests.
6. **Using Hashmap**: Using Hashmap here for better time complexity when storing data locally in application. When storing data in database, using List will represent data in better way. 

---

### **Adding or Borrowing Books**

1. **Adding Books (via `BookServiceTest` or `Main.java`)**:
   To add books to the system, you can use the following code:
   ```java
   bookService.addBook(new Book("111222", "Clean Code", "Robert C. Martin", 2008, 3, 3));
   
2. **Borrowing Books (via LoanServiceTest)**
   To borrow a book using the `LoanService`, you can call the `borrowBook` method with the member ID and the ISBN of the book. Here is an example:
   ```java
   loanService.borrowBook("Member001", "111222");

## **Technologies Used**

- **Java 8+**: Core language used for the application, utilizing features such as **Streams API**, **LocalDate** for date handling, and **ConcurrentHashMap** for thread-safe data storage.
- **JUnit 5**: Framework used for unit testing, ensuring that the core functionality of the services (e.g., BookService, LoanService) works as expected.
- **ConcurrentHashMap**: Used to store data in a thread-safe way, allowing for concurrent access without issues.
- **Streams API**: Utilized for efficient and functional-style data manipulation, especially for filtering and searching books or loans.

---

## **Unit Test Coverage**

### **BookService Tests**:
- **Add a new book**: Ensure that a new book can be successfully added to the inventory.
- **Search for books by ISBN, title, or author**: Verify that the search functionality works for different attributes of the book (ISBN, title, author).
- **Update book details**: Test updating book details such as title, author, and total copies, ensuring the changes are correctly reflected.
- **Remove a book from inventory**: Test the functionality to remove a book from the inventory and verify that it's no longer available in the system.

### **LoanService Tests**:
- **Borrow a book successfully**: Test borrowing a book, ensuring that the available copies are decremented, and the loan is associated with the member.
- **Borrow more than the allowed limit of 3 books**: Verify that the system prevents a member from borrowing more than 3 books at a time.
- **Return a book and update availability**: Test returning a book, ensuring the available copies of the book are updated and the loan is removed.
- **Calculate late fees for overdue returns**: Ensure that late fees are calculated based on the overdue days and are applied correctly when returning a late book.
- **Attempt to borrow a book with no available copies**: Test the case where a member tries to borrow a book with no available copies, ensuring the system responds with an appropriate error message.

---