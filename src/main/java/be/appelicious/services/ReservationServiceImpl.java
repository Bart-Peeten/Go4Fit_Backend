package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.filters.Filter;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repo;
    private Filter filter;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repo,
                                  Filter filter) {
        this.repo = repo;
        this.filter = filter;
    }

    @Override
    public Iterable<Reservation> getAllReservations() {
        return repo.findAll();
    }

    @Override
    public Reservation getReservationById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Reservation addNewReservation(Reservation reservation) {
        return repo.save(reservation);
    }

    @Override
    public Iterable<Reservation> getReservationByDate(Date date) {
        return repo.findReservationsByDate(date);
    }

    @Override
    public Iterable<Reservation> getReservationByDateAndTime(Date date, Time time) {
        return repo.findReservationsByDateAndTime(date, time);
    }

    @Override
    public List<String> getNamesFromReservationByDateAndTime(Date date, Time time) {
        Iterable<Reservation> reservations = repo.findReservationsByDateAndTime(date, time);
        List<String> Names = filter.filterFirstAndLastNameFromReservations(reservations);
        return  null;
    }
}
