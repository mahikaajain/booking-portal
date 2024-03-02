package com.core.moviebookingportal.controller;

import com.core.moviebookingportal.db.entities.Theatre;
import com.core.moviebookingportal.service.TheatreService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;


@RestController
@RequestMapping("/portal")
public class TheatreController {

@Autowired
TheatreService theatreService;

    @GetMapping(path = "/cities")
    public List<String> getCities() {
        return theatreService.getAllCities();
    }

    @GetMapping(path = "/theatres")
    public List<Theatre> getAllTheatresInCity(@NonNull @QueryParam("city") final String city) {
        return theatreService.getAllTheatres(city);
    }


    @GetMapping(path = "/movies")
    public List<String> getAllMoviesByTheatreId(@NonNull @QueryParam("theatreId") final Long theatreId) {
        return theatreService.getAllMoviesByTheatreId(theatreId);
    }
}
