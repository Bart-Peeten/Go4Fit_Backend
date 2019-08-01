package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.Filters;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repo;
    private Filters filter;

    public ReservationServiceImpl(ReservationRepository repo, Filters filter) {
        this.repo = repo;
        this.filter = filter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return repo.findAll();
    }

    @Override
    public List<Reservation> getReservationsByDateAndTime(LocalDate date, LocalTime time) {
        return repo.findAllByDateAndTime(date, time);
    }

    @Override
    public long getNumberOfReservations(LocalDate date, LocalTime time) {
        return repo.countAllByDateAndTime(date, time);
    }

    @Override
    public List<Reservation> getReservationById(long id) {
        return repo.findAllById(id);
    }

    @Override
    public List<Reservation> getReservationByDate(LocalDate date) {
        return repo.findAllByDate(date);
    }

    @Override
    public List<String> getNamesFromReservationByDateAndTime(LocalDate date, LocalTime time) {
        List<Reservation> result = repo.findAllByDateAndTime(date, time);

        return filter.filterFullName(result);
    }

    @Override
    public Reservation addNewReservation(Reservation reservation) {
        return repo.save(reservation);
    }
}
