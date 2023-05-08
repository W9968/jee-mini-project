package studio.wala.mini.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "uid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "role", nullable = false)
    private String role;

    public User() {
    }
    public User(String username, String password, String email, String image, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.role = role;
    }
    public User(Integer id, String username, String password, String email, String image, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
