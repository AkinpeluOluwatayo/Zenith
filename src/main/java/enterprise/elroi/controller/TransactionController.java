package enterprise.elroi.controller;

import enterprise.elroi.dto.requests.TransactionRequests;
import enterprise.elroi.dto.responses.TransactionResponses;
import enterprise.elroi.services.TransactionServiceInterface.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zenith/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceInterface transactionService;

    @PostMapping("/add")
    public ResponseEntity<TransactionResponses> createTransaction(@RequestBody TransactionRequests request) {
        return new ResponseEntity<>(transactionService.createTransaction(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponses>> getUserTransactions() {
        return ResponseEntity.ok(transactionService.getTransactionsForCurrentUser());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}