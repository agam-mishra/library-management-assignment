package BookManagement.models;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private int totalCopies;
    private int availableCopies;

    // Constructor to initialize all fields
    public Book(String isbn, String title, String author, int publicationYear, int totalCopies, int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    // Getters and setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
               "ISBN='" + isbn + '\'' +
               ", Title='" + title + '\'' +
               ", Author='" + author + '\'' +
               ", Publication Year=" + publicationYear +
               ", Total Copies=" + totalCopies +
               ", Available Copies=" + availableCopies +
               '}';
    }
}
