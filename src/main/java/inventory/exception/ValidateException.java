package inventory.exception;
public class ValidateException extends RuntimeException{
    public ValidateException(String errorMessage) {
        super(errorMessage);
    }
}