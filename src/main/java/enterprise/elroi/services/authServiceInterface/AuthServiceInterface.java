package enterprise.elroi.services.authServiceInterface;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponses;
import enterprise.elroi.dto.responses.UserResponses;

public interface AuthServiceInterface {

    UserResponses register(UserRequests registerRequest);

    UserResponses login(UserRequests loginRequest);

    boolean validateToken(String token);

    UserResponses getCurrentUser();
}