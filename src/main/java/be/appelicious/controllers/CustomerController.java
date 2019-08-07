package be.appelicious.controllers;

import be.appelicious.Helpers.RoleHelper;
import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;
import be.appelicious.interfaces.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class CustomerController {

    private BCryptPasswordEncoder encoder;
    private CustomerService service;
    private Logger logger;

    public CustomerController(CustomerService service) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(ReservationController.class);
        this.encoder = new BCryptPasswordEncoder();
    }

    // Endpoint to get all users.
    @GetMapping(path = "/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> result = service.getAllUsers();
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(path = "/login")
    public ResponseEntity<User> doesUserExist(@RequestParam String useremail,
                                              @RequestParam String userPassword) {
        User result = service.findByEmail(useremail);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path = "/admin")
    public ResponseEntity<Boolean> isUserAdmin(@RequestParam String useremail,
                                               @RequestParam String userPassword) {
        User result = service.findByEmail(useremail);
        if (result != null) {
            if (result.getRole().equals("ROLE_ADMIN")) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/registration", consumes = "application/json")
    public ResponseEntity<User> addNewCustomer(@RequestBody User user) {
        /* If a customer signs up there will no role be passed in the Customer object
         *  so, if the role field is empty it will be filled with the USER role.
         *  Otherwise the creation of a new user will be done by Postman to create the admin accounts. */
            User newUser = new User();
        if (!user.getRole().isPresent()) {
            newUser.setRole(RoleHelper.USER);
        } else {
            newUser.setRole(user.getRole().orElse(null));
        }
        System.out.println("De inkomende user is: ");
        System.out.println(user.getFirstName() + " " + user.getLastName());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setEnabled(user.getEnabled());
        String paswd = user.getPassword();
        String encryptedPaswd = encoder.encode(paswd);
        newUser.setPassword(encryptedPaswd);

        User result = service.save(newUser);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<User> removeUser(@RequestParam
                                                   String firstname,
                                           @RequestParam
                                                   String lastname) {
        boolean result = service.removeUser(firstname, lastname);

        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
