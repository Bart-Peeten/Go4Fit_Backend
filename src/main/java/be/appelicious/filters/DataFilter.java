package be.appelicious.filters;

import be.appelicious.domain.User;
import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.Filters;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataFilter implements Filters {

    public List<String> filterFullName(List<Reservation> reservations){
        List<String> customers = new ArrayList<>();
        List<User> reservationUsers = reservations.get(0).getUsers();
        for (int i = 0; i < reservationUsers.size(); i++){
            String firstName = reservationUsers.get(i).getFirstName();
            String lastName = reservationUsers.get(i).getLastName();
            String fullName = firstName + " " + lastName;
            customers.add(fullName);
        }

        return customers;
    }
}
