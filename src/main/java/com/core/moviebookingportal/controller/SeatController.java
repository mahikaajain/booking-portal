package com.core.moviebookingportal.controller;

import com.core.moviebookingportal.db.entities.Seat;
import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.service.SeatService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    SeatService seatService;

    @GetMapping
    public List<Seat> getAllAvailableSeats(@NonNull @QueryParam("showId") final Long showId) {
        return seatService.getAllAvailableSeats(showId);
    }

    @DeleteMapping(path = "/deleteSeats")
    public void deleteAllSeatsByShowId(@NonNull @QueryParam("showId") final Long showId) {
        seatService.deleteAllSeatsByShowId(showId);
    }
}
