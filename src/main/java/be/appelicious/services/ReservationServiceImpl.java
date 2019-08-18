package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;
import be.appelicious.interfaces.Filters;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.CustomerRepository;
import be.appelicious.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bart Peeten
 */

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
    @Transactional(readOnly = true)
    public List<List<String>> getReservationNamesForGivenWeek(List<LocalDate> dates) {
        int index = 0;
        String[][] timesOfWeekList = filltimes();
        List<List<String>> reservationList = new ArrayList<>();
        for (LocalDate item : dates) {
            for (int i = 0; i < timesOfWeekList[index].length; i++) {
                List<Reservation> result = repo.findAllByDateAndTime(item, LocalTime.parse(timesOfWeekList[index][i]));
                if (!result.isEmpty()) {
                    List<String> namesList = parseFullNames(result);
                    reservationList.add(namesList);
                } else {
                    List<String> emptyList = new ArrayList<>();
                    emptyList.add(" ");
                    reservationList.add(emptyList);
                }
            }
            index++;
        }

        return reservationList;
    }

    @Override
    @Transactional
    public List<Integer> getReservationNumbersForGivenWeek(List<LocalDate> datesOfWeek) {
        int index = 0;
        String[][] timesOfWeekList = filltimes();
        List<Integer> reservationList = new ArrayList<>();
        for (LocalDate item : datesOfWeek) {
            for (int i = 0; i < timesOfWeekList[index].length; i++) {
                List<Reservation> result = repo.findAllByDateAndTime(item, LocalTime.parse(timesOfWeekList[index][i]));
                if (!result.isEmpty()) {
                    List<String> namesList = parseFullNames(result);
                    reservationList.add(namesList.size());
                } else {
                    reservationList.add(0);
                }
            }
            index++;
        }

        return reservationList;
    }

    @Override
    public List<Boolean> getIsParticipantReserved(String firstname, String lastname, List<LocalDate> datesOfWeek) {
        int index = 0;
        String[][] timesOfWeekList = filltimes();
        List<Boolean> isReservedList = new ArrayList<>();
        for (LocalDate item : datesOfWeek) {
            for (int i = 0; i < timesOfWeekList[index].length; i++) {
                List<Reservation> result = repo.findAllByDateAndTime(item, LocalTime.parse(timesOfWeekList[index][i]));
                if (!result.isEmpty()) {
                    for (Reservation reservation : result) {
                        if (!reservation.getUsers().isEmpty()) {
                            boolean userReserved = false;
                            for (User user : reservation.getUsers()) {
                                if (user.getFirstName().equals(firstname) &&
                                        user.getLastName().equals(lastname)) {
                                    userReserved = true;
                                    break;
                                } else {
                                    userReserved = false;
                                }
                            }
                            isReservedList.add(userReserved);
                        } else {
                            isReservedList.add(false);
                        }
                    }
                } else {
                    isReservedList.add(false);
                }
            }
            index++;
        }

        return isReservedList;
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
            reservation.getUsers().forEach(item -> {
                item.setUserId(getIdOfExistingUser(item));
                reservationResult.getUsers().add(item);
            });

            return repo.save(reservationResult);
        }
    }

    @Override
    @Transactional
    public Reservation addNewReservationWithOnlyFullName(String firstname, String lastname, LocalDate date, LocalTime time) {
        List<User> userList = new ArrayList<>();
        User newUser = customerRepository.findByFirstNameAndLastName(firstname, lastname);
        if (newUser != null) {
            userList.add(newUser);
        }

        Reservation reservationResult = repo.findByDateAndTime(date, time);
        // If the result is null, this means this reservation is not yet existing so the reservation can be saved as is.
        if (reservationResult == null) {
            Reservation newReservation = new Reservation();
            newReservation.setDate(date);
            newReservation.setTime(time);
            newReservation.setUsers(userList);
            return repo.save(newReservation);
        } else {
            reservationResult.getUsers().add(newUser);
            return repo.save(reservationResult);
        }
    }

    @Override
    @Transactional
    public Reservation removeUserFromReservation(String firstname, String lastname, LocalDate date, LocalTime time) {
        // First get reservation for given date and time.
        Reservation reservationResult = repo.findByDateAndTime(date, time);

        // fetch existing names from reservation result and find the given user in the fetch result.
        String givenFullname = firstname.toLowerCase() + " " + lastname.toLowerCase();
        List<String> namesList = new ArrayList<>();
        reservationResult.getUsers().forEach(item -> namesList.add(item.getFirstName().toLowerCase() + " " + item.getLastName().toLowerCase()));
        int index = namesList.indexOf(givenFullname);

        // If the array of reservationResult.getUsers() is empty
        // or the index is not in reservationResult.getUsers() range
        // return the original reservationResult.getUsers().
        if (reservationResult.getUsers() == null
                || index < 0
                || index >= reservationResult.getUsers().size()) {

            return repo.save(reservationResult);
        }
        // Create another array
        List<User> tmpArray = new ArrayList<>();
        // Copy the elements except the index
        // from original array to the other array
        int size = reservationResult.getUsers().size();
        for (int i = 0; i < size; i++) {
            if (i == index) {
                continue;
            }
            tmpArray.add(reservationResult.getUsers().get(i));
        }
        reservationResult.setUsers(tmpArray);

        return repo.save(reservationResult);
    }

    private long getIdOfExistingUser(User user) {
        User result = customerRepository.findByEmail(user.getEmail());

        return result.getUserId();
    }

    private List<String> parseFullNames(List<Reservation> result) {
        List<String> tmpList = new ArrayList<>();
        result.forEach(item -> {
            List<User> userList = item.getUsers();
            userList.forEach(user -> {
                String fullName = user.getFirstName() + " " + user.getLastName();
                tmpList.add(fullName);
            });
        });

        return tmpList;
    }

    private String[][] filltimes() {
        String tuesday[] = {"19:00", "20:00"};
        String wednesday[] = {"09:00", "19:00", "20:00"};
        String thursday[] = {"19:00"};
        String sunday[] = {"08:00", "09:00", "10:00"};

        String[][] tmpArray = new String[][]{tuesday, wednesday, thursday, sunday};

        return tmpArray;
    }
}
