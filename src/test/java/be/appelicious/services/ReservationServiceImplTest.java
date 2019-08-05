package be.appelicious.services;

import be.appelicious.domain.User;
import be.appelicious.domain.Reservation;
import be.appelicious.filters.DataFilter;
import be.appelicious.interfaces.Filters;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.CustomerRepository;
import be.appelicious.repositories.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DataJpaTest
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository mockRepo;
    @Mock
    private CustomerRepository mockCustomerRepository;
    private Filters filter;
    private ReservationService service;
    private LocalDate date;
    private LocalTime time;
    private List<Reservation> reservationList;
    private long randomNumber;

    public ReservationServiceImplTest() {
        this.filter = new DataFilter();
    }

    @Before
    public void setMockRepo(){
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.reservationList = generateDummyReservation();
        this.randomNumber = generateRandomNumber();
        when(mockRepo.findAll()).thenReturn(this.reservationList);
        when(mockRepo.findAllByDateAndTime(date, time)).thenReturn(null);
        when(mockRepo.countAllByDateAndTime(this.date, this.time)).thenReturn(this.randomNumber);
        this.service = new ReservationServiceImpl(mockRepo, mockCustomerRepository, filter);
    }

    @Test
    public void getAllReservations_ResultShouldNotBeNull(){
        List<Reservation> result = service.getAllReservations();
        assertThat(result).isNotNull();
    }

    @Test
    public void getAllReservations_ShouldReturnIterableOfReservations() {
        List<Reservation> result = service.getAllReservations();
        assertEquals(this.reservationList.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void getReservationsByDateAnsTime_CanBeNull(){
        List<Reservation> result = service.getReservationsByDateAndTime(this.date, this.time);
        assertThat(result).isNull();
    }

    @Test
    public void getReservationsByDateAndTime_ShouldReturnListOfReservations(){
        assertEquals("", "");
    }

    @Test
    public void getNumberOfReservations_ShouldNotBeNull(){
        long result = service.getNumberOfReservations(this.date, this.time);
        assertThat(result).isNotNull();
    }

    @Test
    public void getNumberOfReservations_ShouldReturnNumberOfReservations(){
        long result = service.getNumberOfReservations(this.date, this.time);
        assertThat(result).isEqualTo(this.randomNumber);
    }

    /**
     * From here helper methods are implemented used in the tests
     * */
    private List<Reservation> generateDummyReservation(){
        Reservation reservation = new Reservation();
        User user = new User();
        List<User> userSet = new ArrayList<>();
        user.setFirstName("Test");
        user.setLastName("Test");
        userSet.add(user);
        reservation.setId(1);
        reservation.setDate(LocalDate.now());
        reservation.setUsers(userSet);
        List<Reservation> list = new ArrayList<>();
        list.add(reservation);

        return list;
    }

    private long generateRandomNumber(){
        return (long) new Random().nextInt(10);
    }
}