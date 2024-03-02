package com.core.moviebookingportal.controller;

import com.core.moviebookingportal.db.entities.Booking;
import com.core.moviebookingportal.dto.CreateBookingResponseDto;
import com.core.moviebookingportal.exception.ApiException;
import com.core.moviebookingportal.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateBookingResponseDto createBooking(@NonNull @QueryParam("showId") final Long showId, @NonNull @QueryParam("userId") final String userId,@NonNull @QueryParam("seatNo") final String seatNo) throws ApiException {
        Booking booking = bookingService.createBooking(showId,userId,seatNo);
        return new CreateBookingResponseDto(booking);
    }

    @GetMapping(path = "/all")
    public List<Booking> getAllBookingByShowId(@NonNull @QueryParam("showId") final Long showId) {
        return bookingService.getAllBookingByShowId(showId);
    }


    @GetMapping(path = "/status")
    public Booking getBookingStatusByShowAndUser( @NonNull @QueryParam("showId") final Long showId,@NonNull @QueryParam("userId") final String userId) throws JsonProcessingException {
        return bookingService.getBookingStatusByShowAndUserId(userId,showId);
    }


}
