package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;
import java.util.List;

public interface Filter {

    List<String> extractFirstAndLastNamesFromReservations(List<Reservation> reservations);
}
