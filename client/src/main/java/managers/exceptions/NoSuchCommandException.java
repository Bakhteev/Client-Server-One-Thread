package managers.exceptions;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String message) {
        super(message);
    }
}
