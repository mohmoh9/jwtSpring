package ml.tamboura.JwtSpring.services;

import ml.tamboura.JwtSpring.entity.User;
import ml.tamboura.JwtSpring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    // Permet d'afficher la liste des utilisateurs
    public List<User> getAllUsers() { return userRepository.findAll(); }
    // Filtre par ID
    public User getUserById(Long id) { return userRepository.findById(id).orElseThrow(); }
    // Permet de créer des utilisateurs
    public User createUser(User user) { return userRepository.save(user); }
    // Modification d'utilisateur
    public User updateUser(Long id, User userData) {
        User user = getUserById(id);
        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        return userRepository.save(user);
    }
    // Suppression d'utilisateur
    public void deleteUser(Long id) { userRepository.deleteById(id); }
}
