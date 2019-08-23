package be.appelicious.services;

import be.appelicious.domain.User;
import be.appelicious.interfaces.CustomerService;
import be.appelicious.repositories.CustomerRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUsers() {
        return customerRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());

        return customerRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public boolean removeUser(String firstname, String lastname) {
        int isremoved = customerRepository.deleteByFirstNameAndLastName(firstname, lastname);

        // If isRemoved is greater then 0 then there is a user deleted, so return true else return false.
        return isremoved > 0;
    }
}
