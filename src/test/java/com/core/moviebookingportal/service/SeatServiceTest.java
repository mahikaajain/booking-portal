package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Seat;
import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.db.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SeatServiceTest {
    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    private Seat seat = new Seat();
    private Seat seat2 = new Seat();

    private Seat seat3 = new Seat();
    private Shows show1 = new Shows();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        show1.setId(1L);
        seat.setId(1L);
        seat.setSeatNo("1A");
        seat.setLockExpirationTime(null);
        seat.setVersion(0L);
        seat.setBooked(Boolean.FALSE);
        seat.setShow(show1);
        seat2.setId(2L);
        seat2.setSeatNo("2A");
        seat2.setLockExpirationTime(null);
        seat2.setVersion(0L);
        seat2.setBooked(Boolean.FALSE);
        seat2.setShow(show1);
        seat3.setId(3L);
        seat3.setSeatNo("3A");
        seat3.setLockExpirationTime(null);
        seat3.setVersion(0L);
        seat3.setBooked(Boolean.FALSE);
        seat3.setShow(show1);
    }


    @Test
    public void getAllAvailableSeats() {
        List<Seat> seatList = new ArrayList<>();
        seatList.add(seat);
        seatList.add(seat2);
        seatList.add(seat3);
        Mockito.when(seatRepository.findByShow_IdAndIsBooked(1L,false)).thenReturn(seatList);
        List<Seat> res =  seatService.getAllAvailableSeats(1L);
        assertEquals(res.size(),seatList.size());
        assertEquals(res.get(0),seatList.get(0));
    }

    @Test
    public void getAllAvailableSeatsTwo() {
        seat2.setBooked(true);
        List<Seat> seatList = new ArrayList<>();
        seatList.add(seat);
        seatList.add(seat3);
        Mockito.when(seatRepository.findByShow_IdAndIsBooked(1L,false)).thenReturn(seatList);
        List<Seat> res =  seatService.getAllAvailableSeats(1L);
        assertEquals(res.size(),2);
        assertEquals(res.get(0),seatList.get(0));
    }
}
