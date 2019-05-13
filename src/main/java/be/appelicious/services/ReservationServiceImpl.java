package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.ReservationRepository;
import org.mockito.Mock;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repo;

    public ReservationServiceImpl(ReservationRepository repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Reservation> getAllReservations() {
        return repo.findAll();
    }
}
