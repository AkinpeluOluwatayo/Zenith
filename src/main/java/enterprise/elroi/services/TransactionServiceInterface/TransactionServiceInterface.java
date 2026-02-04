package enterprise.elroi.services.TransactionServiceInterface;

import enterprise.elroi.dto.requests.TransactionRequests;
import enterprise.elroi.dto.responses.TransactionResponses;
import java.util.List;

public interface TransactionServiceInterface {
    TransactionResponses createTransaction(TransactionRequests request);
    List<TransactionResponses> getTransactionsForCurrentUser();
    void deleteTransaction(String transactionId);
}