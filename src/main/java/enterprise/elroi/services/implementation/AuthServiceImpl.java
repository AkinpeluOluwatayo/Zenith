package enterprise.elroi.services.implementation;

import enterprise.elroi.data.model.User;
import enterprise.elroi.data.repository.UserRepository;
import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponses;
import enterprise.elroi.exceptions.authException.CurrentUserNotFoundException;
import enterprise.elroi.exceptions.authException.NoUserCurrentlyLoginException;
import enterprise.elroi.exceptions.authException.UserAlreadyExistException;
import enterprise.elroi.exceptions.authException.UserNotFoundException;
import enterprise.elroi.security.JwtUtils;
import enterprise.elroi.security.UserPrincipal;
import enterprise.elroi.services.authServiceInterface.AuthServiceInterface;
import enterprise.elroi.utils.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServiceInterface {

    private final UserRepository userRepository;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthMapper mapper,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserResponses register(UserRequests registerRequest) {

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }


        User user = mapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User savedUser = userRepository.save(user);


        UserPrincipal userPrincipal = UserPrincipal.build(savedUser);
        String jwt = jwtUtils.generateJwtToken(userPrincipal);

        UserResponses response = mapper.toUserResponse(savedUser);
        response.setToken(jwt);
        response.setMessage("User registered and logged in successfully");

        return response;
    }

    @Override
    public UserResponses login(UserRequests loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userPrincipal);

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserResponses response = mapper.toUserResponse(user);
        response.setToken(jwt);
        response.setMessage("Login successful");
        return response;
    }

    @Override
    public UserResponses getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new NoUserCurrentlyLoginException("No user is currently logged in");
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new CurrentUserNotFoundException("User not found"));

        return mapper.toUserResponse(user);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }
}