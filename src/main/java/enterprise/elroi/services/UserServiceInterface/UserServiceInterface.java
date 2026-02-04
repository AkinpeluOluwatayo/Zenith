package enterprise.elroi.services.UserServiceInterface;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponses;

public interface UserServiceInterface {

    UserResponses getUserById(String id);

    UserResponses updateProfile(String id, UserRequests updateRequest);

    UserResponses getUserByEmail(String email);
}