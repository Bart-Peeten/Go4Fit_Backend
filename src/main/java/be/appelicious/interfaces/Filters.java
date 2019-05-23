package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;
import java.util.List;

public interface Filters {

    List<String> extractFirstAndLastNamesFromReservations(List<Reservation> reservations);
}
