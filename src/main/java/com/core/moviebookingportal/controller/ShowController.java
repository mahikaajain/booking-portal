package com.core.moviebookingportal.controller;


import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.dto.AllShowResponseDto;
import com.core.moviebookingportal.service.ShowService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/theatre")
public class ShowController {

    @Autowired
    ShowService showService;


    @GetMapping(path = "/shows")
    public List<AllShowResponseDto> getAllShows(@NonNull @QueryParam("theatreId") final Long theatreId, @QueryParam("movieName") final String movieName) {
        return showService.getAllShows(theatreId, movieName);
    }

    @DeleteMapping(path = "/deleteShow")
    public Shows deleteByShowId(@NonNull @QueryParam("showId") final Long showId) throws Exception {
        return showService.deleteByShowId(showId);
    }

    @DeleteMapping(path = "/deleteMovie")
    public List<Shows> deleteMovieByTheatreId(@NonNull @QueryParam("theatreId") final Long theatreId, @NonNull @QueryParam("movieName") final String movieName) throws Exception {
        return showService.deleteMovieByTheatreId(theatreId,movieName);
    }

}
