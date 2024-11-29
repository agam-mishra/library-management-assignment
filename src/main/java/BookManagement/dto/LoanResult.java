package BookManagement.dto;

public class LoanResult {
    private final boolean success;
    private final String message;

    public LoanResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
