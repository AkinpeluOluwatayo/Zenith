package enterprise.elroi.security;

import enterprise.elroi.data.model.User;
import enterprise.elroi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return UserPrincipal.build(user);
    }

    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(String.valueOf(UUID.fromString(id)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
        return UserPrincipal.build(user);
    }
}