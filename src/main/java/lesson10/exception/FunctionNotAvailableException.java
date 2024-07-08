package lesson10.exception;

public class FunctionNotAvailableException extends RuntimeException {

    public FunctionNotAvailableException(String message) {
        super(message);
    }
}
