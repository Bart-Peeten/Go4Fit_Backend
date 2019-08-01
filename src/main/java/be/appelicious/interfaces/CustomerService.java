package be.appelicious.interfaces;

import be.appelicious.domain.User;

public interface CustomerService {

    User save(User user);

    User findByEmail(String email);
}
