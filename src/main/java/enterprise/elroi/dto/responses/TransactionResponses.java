package enterprise.elroi.dto.responses;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponses {
    private String id;
    private String description;
    private Double amount;
    private String type;
    private String category;
    private LocalDateTime date;
    private String formattedDate;
    private String userId; // <--- ADD THIS LINE

}