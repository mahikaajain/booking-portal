package com.core.moviebookingportal.dto;

import com.core.moviebookingportal.db.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingResponseDto {

    private long id;

    private String user;

    private String status;

    private String bookedSeats;

    private Long show_id;

    private String screenName;

    private String theatre_name;

    //todo time add


    public CreateBookingResponseDto(Booking booking){
        this.id = booking.getId();
        this.user = booking.getUser();
        this.status = booking.getStatus();
        this.bookedSeats = booking.getSeats();
        this.show_id = booking.getShow().getId();
        this.screenName = booking.getShow().getScreenName();
        this.theatre_name = booking.getShow().getTheatre().getTheatreName();
    }
}
