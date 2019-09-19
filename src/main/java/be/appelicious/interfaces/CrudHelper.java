package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;

public interface CrudHelper {
    boolean isAlreadyReserved(User item, Reservation reservationResult);
}
