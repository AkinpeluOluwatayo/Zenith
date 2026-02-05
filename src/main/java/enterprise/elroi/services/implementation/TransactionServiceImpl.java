package enterprise.elroi.services.implementation;

import enterprise.elroi.data.model.Transaction;
import enterprise.elroi.data.model.User;
import enterprise.elroi.data.repository.TransactionRepository;
import enterprise.elroi.data.repository.UserRepository;
import enterprise.elroi.dto.requests.TransactionRequests;
import enterprise.elroi.dto.responses.TransactionResponses;
import enterprise.elroi.exceptions.transactionException.LoggedInUserNotFoundInDatabaseException;
import enterprise.elroi.exceptions.transactionException.TransactionNotFoundException;
import enterprise.elroi.exceptions.transactionException.UnauthorizedTransactionException;
import enterprise.elroi.security.UserPrincipal;
import enterprise.elroi.services.TransactionServiceInterface.TransactionServiceInterface;
import enterprise.elroi.utils.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionServiceInterface {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    @Transactional
    public TransactionResponses createTransaction(TransactionRequests request) {

        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new LoggedInUserNotFoundInDatabaseException("Logged in user not found in database"));

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setUser(user);

        Transaction saved = transactionRepository.save(transaction);
        return mapper.toResponse(saved);
    }

    @Override
    public List<TransactionResponses> getTransactionsForCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<Transaction> transactions = transactionRepository.findByUserId(principal.getId());
        return mapper.toResponseList(transactions);
    }

    @Override
    @Transactional
    public void deleteTransaction(String transactionId) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

        if (!transaction.getUser().getId().equals(principal.getId())) {
            throw new UnauthorizedTransactionException("Unauthorized: You do not own this transaction");
        }

        transactionRepository.delete(transaction);
    }
}