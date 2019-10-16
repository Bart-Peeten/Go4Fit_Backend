package be.appelicious.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "go4fit_customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "firstname")
    @NotBlank(message = "Voornaam is verplicht!")
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "Achternaam is verplicht!")
    private String lastName;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "phone")
    @NotBlank
    private String phone;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Reservation> reservations = new ArrayList<>();

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    public void setRole(String role) {
        this.role = role;
    }
}
