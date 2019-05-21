package be.appelicious.filters;

import be.appelicious.domain.Customer;
import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.Filter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataFilter implements Filter {

    public List<String> extractFirstAndLastNamesFromReservations(List<Reservation> reservations){
        List<String> customers = new ArrayList<>();
        List<Customer> reservationCustomers = reservations.get(0).getCustomers();
        for (int i = 0; i < reservationCustomers.size(); i++){
            String firstName = reservationCustomers.get(i).getFirstName();
            String lastName = reservationCustomers.get(i).getLastName();
            String fullName = firstName + " " + lastName;
            customers.add(fullName);
        }

        return customers;
    }
}
