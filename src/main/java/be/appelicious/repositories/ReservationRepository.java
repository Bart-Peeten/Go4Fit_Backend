package be.appelicious.repositories;

import be.appelicious.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByDateAndTime(LocalDate date, LocalTime time);
    List<Reservation> findAllById(long id);
    List<Reservation> findAllByDate(LocalDate date);
    Reservation findByDateAndTime(LocalDate date, LocalTime time);
    long countAllByDateAndTime(LocalDate date, LocalTime time);
}
