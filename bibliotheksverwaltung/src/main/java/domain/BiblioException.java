package domain;

public class BiblioException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    
    public enum ErrorCode {
        DB_CONNECTION_ERROR,
        ITEM_NOT_FOUND,
        VALIDATION_ERROR,
        AUTHENTICATION_ERROR,
        BUSINESS_RULE_VIOLATION,
        GENERAL_ERROR
    }
    
    public BiblioException(String message) {
        super(message);
        this.errorCode = ErrorCode.GENERAL_ERROR;
    }
    
    public BiblioException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BiblioException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ErrorCode.GENERAL_ERROR;
    }
    
    public BiblioException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}