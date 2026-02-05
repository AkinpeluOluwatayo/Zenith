package enterprise.elroi.exceptions.authException;

public class NoUserCurrentlyLoginException extends UserNotFoundException {
    public NoUserCurrentlyLoginException(String message) {
        super(message);
    }
}
