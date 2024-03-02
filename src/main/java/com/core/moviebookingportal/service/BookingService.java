package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Booking;
import com.core.moviebookingportal.db.entities.Seat;
import com.core.moviebookingportal.db.repository.BookingRepository;
import com.core.moviebookingportal.db.repository.SeatRepository;
import com.core.moviebookingportal.db.repository.ShowRepository;
import com.core.moviebookingportal.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private static final int LOCK_DURATION_SECONDS = 5;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatRepository seatRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public Booking createBooking(final Long showId, final String userId, final String seatNo) throws ApiException {
        //1A,2A,3A
        List<String> seats = List.of(seatNo.split(","));
        Booking booking = new Booking();
        try{
            for(String seat: seats){
                Seat wantedSeat = seatRepository.findBySeatNoAndShow_Id(seat,showId);
                if(wantedSeat.isBooked()){
                    throw new ApiException("Seat" + wantedSeat.getSeatNo() + " for showId " + showId + " is already booked.", HttpStatus.CONFLICT);
                }
                if(wantedSeat.getLockExpirationTime() == null || wantedSeat.getLockExpirationTime().isBefore(LocalDateTime.now())){
                    wantedSeat.setLockExpirationTime(LocalDateTime.now().plusSeconds((LOCK_DURATION_SECONDS)));
                    wantedSeat.setBooked(true);
                    seatRepository.save(wantedSeat);
                }else{
                    throw new ApiException("Seat" + wantedSeat.getSeatNo() + " for showId " + showId + " is already locked and cannot be booked.",HttpStatus.LOCKED);
                }
            }
            booking.setSeats(seatNo);
            booking.setShow(showRepository.findById(showId).get());
            booking.setUser(userId);
            booking.setStatus("confirmed");
            booking = bookingRepository.save(booking);
            return booking;
        }
        catch (Exception ex){
            throw new ApiException("Booking creation failed: "+ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }



    public List<Booking> getAllBookingByShowId(final Long showId) {
        return bookingRepository.findByShow_Id(showId);
    }


    public Booking getBookingStatusByShowAndUserId(final String userId, final Long showId) {
        return bookingRepository.findByUserAndShowId(userId, showId);
    }


}
