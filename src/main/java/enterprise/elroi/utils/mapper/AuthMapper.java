package enterprise.elroi.utils.mapper;

import enterprise.elroi.data.model.User;
import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponses;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthMapper {

    public User toUser(UserRequests request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        // Password will be hashed in the service layer
        user.setTransactions(new ArrayList<>());
        return user;
    }

    public UserResponses toUserResponse(User user) {
        UserResponses response = new UserResponses();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());

        // Return transaction count or map them to IDs if necessary
        if (user.getTransactions() != null) {
            response.setMessage("Found " + user.getTransactions().size() + " transactions");
        } else {
            response.setMessage("Success");
        }

        return response;
    }
}