package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.db.entities.Theatre;
import com.core.moviebookingportal.db.repository.ShowRepository;
import com.core.moviebookingportal.db.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowRepository showRepository;

    public List<String> getAllCities() {
        return theatreRepository.findDistinctCity();
    }

    public List<Theatre> getAllTheatres(final String city) {
        return theatreRepository.findTheatreNameByCity(city);
    }

    public List<String> getAllMoviesByTheatreId(final Long theatreId) {
        List<Shows> showsList = showRepository.findByTheatre_Id(theatreId);
        return showsList.stream().map(Shows::getMovieName).distinct().collect(Collectors.toList());
    }

}
