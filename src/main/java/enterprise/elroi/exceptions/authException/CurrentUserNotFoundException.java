package enterprise.elroi.exceptions.authException;

public class CurrentUserNotFoundException extends NoUserCurrentlyLoginException {
    public CurrentUserNotFoundException(String message) {
        super(message);
    }
}
