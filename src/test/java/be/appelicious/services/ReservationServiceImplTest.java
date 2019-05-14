package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.ReservationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReservationServiceImplTest {

    public ReservationServiceImplTest() {
    }

    @Mock
    private ReservationRepository mockRepo;

    @InjectMocks
    ReservationService service;

    /*@BeforeEach
    void setMockRepo(){
        when(mockRepo.findAll()).thenReturn(createMockIterable());
    }*/

    @Test
    public void getAllReservations() {
        assertEquals("", "False");
        //assertEquals(createMockIterable(), service.getAllReservations());
    }

    private Iterable<Reservation> createMockIterable(){
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setDate(new Date());
        List<Reservation> list = new ArrayList<>();
        list.add(reservation);

        return (Iterable<Reservation>) list.iterator();
    }
}