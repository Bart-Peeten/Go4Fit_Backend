package be.appelicious.services;

import be.appelicious.domain.Reservation;
import be.appelicious.interfaces.ReservationService;
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

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DataJpaTest
public class ReservationServiceImplTest {

    public ReservationServiceImplTest() {
    }

    @Mock
    private ReservationRepository mockRepo;
    private ReservationService service;

    @Before
    public void setMockRepo(){
        when(mockRepo.findAll()).thenReturn(createMockIterable());
        this.service = new ReservationServiceImpl(mockRepo );
    }

    @Test
    public void getAllReservations_ResultShouldNotBeNull(){
        List<Reservation> result = service.getAllReservations();
        assertThat(result).isNotNull();
    }

    @Test
    public void getAllReservations_ShouldReturnIterableOfReservations() {
        List<Reservation> result = service.getAllReservations();
        assertEquals(createMockIterable().get(0).getId(), result.get(0).getId());

    }

    private List<Reservation> createMockIterable(){
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setDate(new Date());
        List<Reservation> list = new ArrayList<>();
        list.add(reservation);

        return list;
    }
}