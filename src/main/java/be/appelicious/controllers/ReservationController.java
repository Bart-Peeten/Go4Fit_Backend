package be.appelicious.controllers;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Reservation>> getAllReservations(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}