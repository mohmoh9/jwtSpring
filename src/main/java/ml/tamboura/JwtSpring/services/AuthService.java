package ml.tamboura.JwtSpring.services;

import ml.tamboura.JwtSpring.config.JwtUtils;
import ml.tamboura.JwtSpring.dto.LoginRequest;
import ml.tamboura.JwtSpring.dto.SignupRequest;
import ml.tamboura.JwtSpring.entity.User;
import ml.tamboura.JwtSpring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public User signup(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user; // retourner l'objet User
    }
}
