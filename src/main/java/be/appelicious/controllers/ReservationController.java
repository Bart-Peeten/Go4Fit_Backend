package be.appelicious.controllers;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/reservation")
public class ReservationController {

    private ReservationService service;

    public ReservationController(ReservationService reservationService) {
        this.service = reservationService;
    }

    @GetMapping(path = "/")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservation = service.getAllReservations();

        if (reservation.spliterator().getExactSizeIfKnown() > 0){
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Reservation>> getReservationById(@PathVariable long id) {
        return new ResponseEntity<>(service.getReservationById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/date")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@RequestParam("date")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                   LocalDate date) {

        return new ResponseEntity<>(service.getReservationByDate(date), HttpStatus.OK);
    }

    @GetMapping(path = "/date_time")
    public ResponseEntity<List<Reservation>> getReservationByDateAndTime(@RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                         LocalDate date,
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                         LocalTime time){
        return new ResponseEntity<>(service.getReservationsByDateAndTime(date, time),
                HttpStatus.OK);
    }

    @GetMapping(path = "/names")
    public ResponseEntity<List<String>> getNamesFromReservationByDateAndTime(@RequestParam
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                             LocalDate date,
                                                                             @RequestParam
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                             LocalTime time){
        return new ResponseEntity<>(service.getNamesFromReservationByDateAndTime(date, time),
                HttpStatus.OK);
    }

    @PostMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody @Valid Reservation reservation) {
        Reservation result = service.addNewReservation(reservation);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}