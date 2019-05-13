package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
import be.appelicious.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository mockRepo;

    @InjectMocks
    ReservationService service;

    /*@BeforeEach
    void setMockRepo(){
        when(mockRepo.findAll()).thenReturn(createMockIterable());
    }*/

    @Test
    void getAllReservations() {
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