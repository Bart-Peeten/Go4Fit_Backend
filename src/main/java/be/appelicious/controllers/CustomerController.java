package be.appelicious.controllers;

import be.appelicious.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users")
public class CustomerController {

    @GetMapping(path = "/user")
    public ResponseEntity<Boolean> doesUserExist(@RequestParam("user") Customer user){
        return null;
    }



}
