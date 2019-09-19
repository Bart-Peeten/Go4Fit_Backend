package be.appelicious.Helpers;

import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;
import be.appelicious.interfaces.CrudHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CrudHelperImpl implements CrudHelper {

    public boolean isAlreadyReserved(User item, Reservation reservationResult) {
        AtomicBoolean isReserved = new AtomicBoolean(false);
        reservationResult.getUsers().forEach(user -> {
            if (user.getEmail().equals(item.getEmail())) {
                isReserved.set(true);
            }
        });
        return isReserved.get();
    }
}
