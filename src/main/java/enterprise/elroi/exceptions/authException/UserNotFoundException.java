package enterprise.elroi.exceptions.authException;

public class UserNotFoundException extends UserAlreadyExistException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
