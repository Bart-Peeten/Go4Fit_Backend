package be.appelicious.filters;

import be.appelicious.domain.Customer;
import be.appelicious.domain.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class Filter {

    public List<String> filterFirstAndLastNameFromReservations(Iterable<Reservation> reservation){
        List<String> names = new ArrayList<>();
        for (Reservation item: reservation) {
            Set<Customer> customers = item.getCustomers();
            for (Customer customer : customers){
                String  fullName = customer.getFirstName() + " " + customer.getLastName();
                System.out.println("In Filter class: " + fullName);
                names.add(fullName);
            }
        }
        return names;
    }
}
