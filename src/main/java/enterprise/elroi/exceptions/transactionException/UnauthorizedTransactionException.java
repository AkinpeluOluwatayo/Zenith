package enterprise.elroi.exceptions.transactionException;

public class UnauthorizedTransactionException extends TransactionNotFoundException {
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
