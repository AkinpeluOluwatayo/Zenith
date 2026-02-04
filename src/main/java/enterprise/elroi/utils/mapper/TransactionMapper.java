package enterprise.elroi.utils.mapper;

import enterprise.elroi.data.model.Transaction;
import enterprise.elroi.dto.responses.TransactionResponses;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    public TransactionResponses toResponse(Transaction transaction) {
        if (transaction == null) return null;

        TransactionResponses response = new TransactionResponses();
        response.setId(transaction.getId());
        response.setDescription(transaction.getDescription());
        response.setAmount(transaction.getAmount());
        response.setType(transaction.getType());
        response.setCategory(transaction.getCategory());
        response.setDate(transaction.getDate());

        if (transaction.getUser() != null) {
            response.setUserId(transaction.getUser().getId());
        }

        return response;
    }

    public List<TransactionResponses> toResponseList(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}