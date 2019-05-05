package be.appelicious.controllers;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping(path = "api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping(path = "/")
    @Transactional(readOnly = true)
    public ResponseEntity<Iterable<Reservation>> getAllReservations() {
        Iterable<Reservation> reservation = service.getAllReservations();

        if (reservation.spliterator().getExactSizeIfKnown() > 0){
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id) {
        return new ResponseEntity<>(service.getReservationById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/date/{date}")
    public ResponseEntity<Iterable<Reservation>> getReservationsByDate(@PathVariable Date date) {
        return new ResponseEntity<>(service.getReservationByDate(date), HttpStatus.OK);
    }

    @GetMapping(path = "/date/{date}/time/{time}")
    public ResponseEntity<Iterable<Reservation>> getReservationByDateAndTime(@PathVariable Date date,
                                                                             @PathVariable Time time){
        return new ResponseEntity<>(service.getReservationByDateAndTime(date, time),
                                    HttpStatus.OK);
    }

    @GetMapping(path = "names/date/{date}/time/{time}")
    public ResponseEntity<List<String>> getNamesFromReservationByDateAndTime(@PathVariable Date date,
                                                                                  @PathVariable Time time){
        return new ResponseEntity<>(service.getNamesFromReservationByDateAndTime(date, time),
                HttpStatus.OK);
    }

    @PostMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody @Valid Reservation reservation){
        Reservation result = service.addNewReservation(reservation);

        if (result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}