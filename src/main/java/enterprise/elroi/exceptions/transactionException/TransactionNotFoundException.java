package enterprise.elroi.exceptions.transactionException;

public class TransactionNotFoundException extends LoggedInUserNotFoundInDatabaseException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
