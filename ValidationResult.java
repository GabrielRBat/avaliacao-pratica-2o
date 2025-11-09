public class ValidationResult {
    
    public final boolean success;
    public final String message;
    public final boolean skipNext;

    public ValidationResult(boolean success, String message) {
        this(success, message, false);
    }

    public ValidationResult(boolean success, String message, boolean skipNext) {
        this.success = success;
        this.message = message;
        this.skipNext = skipNext;
    }
}
