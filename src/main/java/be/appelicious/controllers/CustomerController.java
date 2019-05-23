package be.appelicious.controllers;

import be.appelicious.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users")
public class CustomerController {

    public ResponseEntity doesUserExsist(Customer user){
        return null;
    }

}
