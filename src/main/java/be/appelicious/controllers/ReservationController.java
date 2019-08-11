package be.appelicious.controllers;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bart Peeten
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/api/reservation")
public class ReservationController {

    private ReservationService service;
    private Logger logger;

    public ReservationController(ReservationService reservationService) {
        this.logger = LoggerFactory.getLogger(ReservationController.class);
        this.service = reservationService;
    }

    /**
     * Endpoint to GET all reservations
     */
    @GetMapping(path = "/")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservation = service.getAllReservations();

        if (reservation.spliterator().getExactSizeIfKnown() > 0) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to GET all reservations by reservation id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Reservation>> getReservationById(@PathVariable long id) {
        return new ResponseEntity<>(service.getReservationById(id), HttpStatus.OK);
    }

    /**
     * Endpoint to GET number of reservations for a given date and time
     */
    @GetMapping(path = "/numberofreservations")
    public ResponseEntity<Long> getNumberOfReservationByDateAndTime(@RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                            LocalDate date,
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                            LocalTime time) {
        Long result = service.getNumberOfReservations(date, time);

        return new ResponseEntity<Long>(result, HttpStatus.OK);
    }

    /**
     * Endpoint to GET all names of a reservations for a given date and time
     */
    @GetMapping(path = "/names")
    public ResponseEntity<List<String>> getNamesFromReservationByDateAndTime(@RequestParam
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                     LocalDate date,
                                                                             @RequestParam
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                                     LocalTime time) {
        return new ResponseEntity<>(service.getNamesFromReservationByDateAndTime(date, time),
                HttpStatus.OK);
    }

    /**
     * Endpoint to GET all data of a reservations for a given week
     *
     * @param datesOfWeek
     * @return List with Reservations of the given week.
     */
    @GetMapping(path = "/weekusers")
    public ResponseEntity<List<List<String>>> getReservationNamesForGivenWeek(@RequestParam
                                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                      List<LocalDate> datesOfWeek) {
        List<List<String>> result = new ArrayList<>();
        result = service.getReservationNamesForGivenWeek(datesOfWeek);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Endpoint to GET all number of a reservations for a given week
     *
     * @param datesOfWeek
     * @return List with number of Reservations of the given week.
     */
    @GetMapping(path = "/weekreservaties")
    public ResponseEntity<List<Integer>> getReservationNumbersForGivenWeek(@RequestParam
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                   List<LocalDate> datesOfWeek) {
        List<Integer> result = new ArrayList<>();
        result = service.getReservationNumbersForGivenWeek(datesOfWeek);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Endpoint to GET if a participant is reserved for a given date and time
     *
     * @param firstname
     * @param lastname
     * @param datesOfWeek
     * @return List with booleans if user is reserved for the given week.
     */
    @GetMapping(path = "/isParticipantReserved")
    public ResponseEntity<List<Boolean>> getIsParticipantReservedByDateAndTime(@RequestParam String firstname,
                                                                         @RequestParam String lastname,
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                 List<LocalDate> datesOfWeek) {
        List<Boolean> result = service.getIsParticipantReserved(firstname, lastname, datesOfWeek);

        return new ResponseEntity<>(result, HttpStatus.OK);
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
                                                                                 LocalTime time) {
        return new ResponseEntity<>(service.getReservationsByDateAndTime(date, time),
                HttpStatus.OK);
    }

    /**
     * Endpoint to POST a new reservation
     */
    @PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody @Valid Reservation reservation) {
        Reservation result = service.addNewReservation(reservation);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to POST a new reservation when only fullname is available.
     */
    @PostMapping(path = "/onlyname")
    public ResponseEntity<Reservation> addNewReservationWithOnlyFullName(@RequestParam String firstname,
                                                                         @RequestParam String lastname,
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                 LocalDate date,
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                                 LocalTime time) {
        Reservation result = service.addNewReservationWithOnlyFullName(firstname, lastname, date, time);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to DELETE a new reservation
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Reservation> removeUserFromReservation(@RequestParam
                                                                         String firstname,
                                                                 @RequestParam
                                                                         String lastname,
                                                                 @RequestParam
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                         LocalDate date,
                                                                 @RequestParam
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                         LocalTime time) {
        Reservation reservation = service.removeUserFromReservation(firstname, lastname, date, time);
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }
}
