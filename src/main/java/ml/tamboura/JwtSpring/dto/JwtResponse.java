package ml.tamboura.JwtSpring.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String nom;
    private String prenom;
    private String username;
    private String email;

    public JwtResponse(String token, String type, String nom, String prenom, String username) {
        this.token = token;
        this.type = type;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
