package enterprise.elroi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequests {
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Type must be INCOME or EXPENSE")
    private String type;

    @NotBlank(message = "Category is required")
    private String category;


}