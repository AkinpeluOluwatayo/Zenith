package enterprise.elroi.exceptions.transactionException;

public class LoggedInUserNotFoundInDatabaseException extends RuntimeException {
    public LoggedInUserNotFoundInDatabaseException(String message) {
        super(message);
    }
}
