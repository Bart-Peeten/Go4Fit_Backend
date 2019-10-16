package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;

import java.util.List;

public interface CrudHelper {
    boolean isAlreadyReserved(User item, Reservation reservationResult);

    String[][] filltimes();

    List<String> parseFullNames(List<Reservation> result);

    List<String> parseCancellationFullNames(List<Reservation> result);
}
