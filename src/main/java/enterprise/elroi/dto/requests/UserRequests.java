package enterprise.elroi.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
public class UserRequests {
    private String fullName;
    private String email;
    private String password;
}