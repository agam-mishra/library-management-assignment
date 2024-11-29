package BookManagement.dto;

public class ReturnResult {
    private final boolean success;
    private final String message;
    private final int lateFee;

    public ReturnResult(boolean success, String message, int lateFee) {
        this.success = success;
        this.message = message;
        this.lateFee = lateFee;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public int getLateFee() { return lateFee; }
}
