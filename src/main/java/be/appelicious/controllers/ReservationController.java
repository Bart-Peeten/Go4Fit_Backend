package be.appelicious.controllers;

import be.appelicious.interfaces.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}