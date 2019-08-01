package be.appelicious.controllers;

import be.appelicious.Helpers.RoleHelper;
import be.appelicious.domain.Customer;
import be.appelicious.interfaces.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/")
public class CustomerController {

    private CustomerService service;
    private Logger logger;
    private BCryptPasswordEncoder encoder;

    public CustomerController(CustomerService service) {
        this.service = service;
        this.logger  = LoggerFactory.getLogger(ReservationController.class);
        this.encoder = new BCryptPasswordEncoder();
    }

    @GetMapping(path = "login")
    public ResponseEntity<Boolean> doesUserExist(@RequestParam("login") Customer user){
        Customer result = service.findByEmail(user.getEmail());
        if (result != null) {
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "registration", consumes = "application/json")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody @Valid Customer customer){
        /* If a customer signs up there will no role be passed in the Customer object
        *  so, if the role field is empty it will be filled with the USER role.
        *  Otherwise the creation of a new user will be done by Postman to create the admin accounts. */
        if (!customer.getRole().isPresent()) {
            customer.setRole(RoleHelper.USER);
        }
        String paswd = customer.getPassword();
        String encryptedPaswd = encoder.encode(paswd);
        customer.setPassword(encryptedPaswd);

        Customer result = service.save(customer);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
