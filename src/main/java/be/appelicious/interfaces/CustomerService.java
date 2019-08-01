package be.appelicious.interfaces;

import be.appelicious.domain.Customer;

public interface CustomerService {

    Customer save(Customer customer);

    Customer findByEmail(String email);
}
