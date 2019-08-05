package be.appelicious.services;

import be.appelicious.domain.User;
import be.appelicious.interfaces.CustomerService;
import be.appelicious.repositories.CustomerRepository;
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

        return customerRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
