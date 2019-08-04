package be.appelicious.services;

import be.appelicious.domain.User;
import be.appelicious.interfaces.CustomerService;
import be.appelicious.repositories.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
