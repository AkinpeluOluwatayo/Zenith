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
        user.setTransactions(new ArrayList<>());
        return user;
    }

    public UserResponses toUserResponse(User user) {
        UserResponses response = new UserResponses();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());


        if (user.getTransactions() != null && !user.getTransactions().isEmpty()) {
            response.setMessage("Found " + user.getTransactions().size() + " transactions");
        } else {
            response.setMessage("Success");
        }

        return response;
    }
}