package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;
import be.appelicious.interfaces.Filters;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.CustomerRepository;
import be.appelicious.repositories.ReservationRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repo;
    private CustomerRepository customerRepository;
    private Filters filter;
    private static final String NO_RESERVATION = "Nog geen reservaties!";

    public ReservationServiceImpl(ReservationRepository repo,
                                  CustomerRepository customerRepository,
                                  Filters filter) {
        this.repo = repo;
        this.filter = filter;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByDateAndTime(LocalDate date, LocalTime time) {
        return repo.findAllByDateAndTime(date, time);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getNumberOfReservations(LocalDate date, LocalTime time) {
        List<String> result = getNamesFromReservationByDateAndTime(date, time);
        Long count = (long) result.size();
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationById(long id) {
        return repo.findAllById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationByDate(LocalDate date) {
        return repo.findAllByDate(date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getNamesFromReservationByDateAndTime(LocalDate date, LocalTime time) {
        List<String> noResults = new ArrayList<>();
        List<Reservation> result = repo.findAllByDateAndTime(date, time);
        if (result.isEmpty()) {
            noResults.add(NO_RESERVATION);
            return noResults;
        }

        return filter.filterFullName(result);
    }

    @Override
    @Transactional
    public Reservation addNewReservation(Reservation reservation) {
        User userResult = null;
        List<User> newUser = reservation.getUsers();
        Reservation reservationResult = repo.findByDateAndTime(reservation.getDate(), reservation.getTime());
        // If the result is null, this means this reservation is not yet existing so the reservation can be saved as is.
        if (reservationResult == null) {
            return repo.save(reservation);
        } else {
            reservation.getUsers().forEach(item -> reservationResult.getUsers().add(item));
            return repo.save(reservationResult);
        }
    }
}
