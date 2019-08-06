package be.appelicious.interfaces;

import be.appelicious.domain.User;

import java.util.List;

public interface CustomerService {
    List<User> getAllUsers();
    User save(User user);
    User findByEmail(String email);
    boolean removeUser(String firstname, String lastname);
}
