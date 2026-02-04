package enterprise.elroi.dto.responses;

import lombok.Data;

import java.util.ArrayList;

@Data

public class UserResponses {
    private String id;
    private String token;
    private String fullName;
    private String email;
    private String message;
    private ArrayList<String> transactionIds;
}