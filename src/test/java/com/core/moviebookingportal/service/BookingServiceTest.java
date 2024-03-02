package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Booking;
import com.core.moviebookingportal.db.entities.Seat;
import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.db.repository.BookingRepository;
import com.core.moviebookingportal.db.repository.SeatRepository;
import com.core.moviebookingportal.db.repository.ShowRepository;
import com.core.moviebookingportal.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.Optional;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private BookingService bookingService;
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
    void testCreateBooking_Success() throws ApiException {

        Long showId = 1L;
        String userId = "dummy";
        String seatNo = "1A,2A,3A";
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("1A",showId)).thenReturn(seat);
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("2A",showId)).thenReturn(seat2);
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("3A",showId)).thenReturn(seat3);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Seat updateSeat = (Seat) invocation.getArguments()[0];
                LocalDateTime time = updateSeat.getLockExpirationTime();
                boolean isBooked = updateSeat.isBooked();
                assertTrue(isBooked);
                seat.setBooked(isBooked);
                seat.setLockExpirationTime(time);
                return seat;
            }
        }).when(seatRepository).save(seat);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Seat updateSeat = (Seat) invocation.getArguments()[0];
                LocalDateTime time = updateSeat.getLockExpirationTime();
                boolean isBooked = updateSeat.isBooked();
                assertTrue(isBooked);
                seat2.setBooked(isBooked);
                seat2.setLockExpirationTime(time);
                return seat2;
            }
        }).when(seatRepository).save(seat2);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Seat updateSeat = (Seat) invocation.getArguments()[0];
                LocalDateTime time = updateSeat.getLockExpirationTime();
                boolean isBooked = updateSeat.isBooked();
                assertTrue(isBooked);
                seat3.setBooked(isBooked);
                seat3.setLockExpirationTime(time);
                return seat3;
            }
        }).when(seatRepository).save(seat3);

        Mockito.when(showRepository.findById(showId)).thenReturn(Optional.of(show1));
        Booking booking = new Booking();
        booking.setSeats(seatNo);
        booking.setShow(show1);
        booking.setUser(userId);
        booking.setStatus("confirmed");
        Mockito.when(bookingRepository.save(Mockito.any())).thenReturn(booking);
        Booking booking1 = bookingService.createBooking(showId,userId,seatNo);
        assertEquals(booking1,booking);
        assertEquals(booking1.getSeats(),"1A,2A,3A");
    }


    @Test
    void testCreateBooking_Failed_Seat_AlreadyBooked()  {

        Long showId = 1L;
        String userId = "dummy";
        String seatNo = "1A,2A,3A";
        seat2.setBooked(true);
        seat2.setVersion(1L);
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("1A",showId)).thenReturn(seat);
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("2A",showId)).thenReturn(seat2);
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("3A",showId)).thenReturn(seat3);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Seat updateSeat = (Seat) invocation.getArguments()[0];
                LocalDateTime time = updateSeat.getLockExpirationTime();
                boolean isBooked = updateSeat.isBooked();
                assertTrue(isBooked);
                seat.setBooked(isBooked);
                seat.setLockExpirationTime(time);
                return seat;
            }
        }).when(seatRepository).save(seat);
        ApiException exception = assertThrows(ApiException.class,
                () -> bookingService.createBooking(showId, userId, seatNo));
        assertEquals(exception.getMessage(),"Booking creation failed: Seat2A for showId 1 is already booked.");
    }

    @Test
    void testCreateBooking_Concurrency(){

        Long showId = 1L;
        String userId = "dummy";
        String seatNo = "1A";
        Mockito.when(showRepository.findById(showId)).thenReturn(Optional.of(show1));
        Mockito.when(seatRepository.findBySeatNoAndShow_Id("1A", showId)).thenReturn(seat);

        Mockito.when(seatRepository.save(Mockito.any()))
                .thenAnswer(invocation -> {
                    Seat savedSeat = invocation.getArgument(0);
                    Long expectedVersion = savedSeat.getVersion();
                    seat.setVersion(seat.getVersion() + 1);
                    if (seat.getVersion() != expectedVersion) {
                        throw new ObjectOptimisticLockingFailureException(Seat.class, savedSeat.getSeatNo());
                    }
                    return null;
                });

        ApiException exception = assertThrows(ApiException.class,
                () -> bookingService.createBooking(showId, userId, seatNo));
        assertEquals(exception.getMessage(),"Booking creation failed: Object of class [com.core.moviebookingportal.db.entities.Seat] with identifier [1A]: optimistic locking failed");
    }

}
