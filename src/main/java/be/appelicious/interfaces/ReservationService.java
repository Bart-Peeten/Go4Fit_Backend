package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;

public interface ReservationService {

    Iterable<Reservation> getAllReservations();
}
