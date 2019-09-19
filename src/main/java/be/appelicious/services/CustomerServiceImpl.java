package be.appelicious.services;

import be.appelicious.Helpers.RoleHelper;
import be.appelicious.domain.User;
import be.appelicious.interfaces.CustomerService;
import be.appelicious.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Logger logger;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
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
    @Secured({RoleHelper.ADMIN, RoleHelper.USER})
    public User findByEmail(String email, String password) {
        logger.info("De gebruiker met  {} probeert aan te melden met passwoord {}.", email, password);
        User result  = customerRepository.findByEmail(email);

        return result;
    }

    @Override
    @Secured(RoleHelper.ADMIN)
    public boolean removeUser(String firstname, String lastname) {
        int isremoved = customerRepository.deleteByFirstNameAndLastName(firstname, lastname);

        // If isRemoved is greater then 0 then there is a user deleted, so return true else return false.
        return isremoved > 0;
    }
}
