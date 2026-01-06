package ml.tamboura.JwtSpring.controller;

import ml.tamboura.JwtSpring.config.JwtUtils;
import ml.tamboura.JwtSpring.entity.User;
import ml.tamboura.JwtSpring.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthService authService, JwtUtils jwtUtils) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
    }


    // Inscription
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest) {

        if (signupRequest.getUsername() == null || signupRequest.getPassword() == null) {
            return ResponseEntity.badRequest()
                    .body("Username and password are required");
        }

        User user = authService.signup(signupRequest);
        return ResponseEntity.ok(
                "User registered successfully: " + user.getUsername()
        );
    }

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(
            @RequestBody LoginRequest loginRequest) {

        User user = authService.login(loginRequest);

        String jwt = jwtUtils.generateToken(user.getUsername());


        JwtResponse response = new JwtResponse(
                jwt,
                user.getUsername(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail()
        );


        return ResponseEntity.ok(response);
    }
}
