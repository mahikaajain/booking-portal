package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.db.entities.Theatre;
import com.core.moviebookingportal.db.repository.ShowRepository;
import com.core.moviebookingportal.db.repository.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private TheatreService theatreService;

    private Theatre theatre = new Theatre();
    private Theatre theatre1 = new Theatre();
    private Theatre theatre2 = new Theatre();
    private Shows show1 = new Shows();
    private Shows show2 = new Shows();
    private Shows show3 = new Shows();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        theatre.setId(1L);
        theatre.setTheatreName("theatre1");
        theatre.setCity("bombay");
        theatre1.setId(2L);
        theatre1.setTheatreName("theatre2");
        theatre1.setCity("bengaluru");
        theatre2.setId(3L);
        theatre2.setTheatreName("theatre4");
        theatre2.setCity("bengaluru");
        show1.setId(1L);
        show1.setMovieName("movie1");
        show1.setScreenName("screen1");
        show1.setStartTime(1709021807000L);
        show1.setDuration(7200L);
        show1.setTheatre(theatre);
        show2.setId(2L);
        show2.setMovieName("movie1");
        show2.setScreenName("screen1");
        show2.setStartTime(1709032607000L);
        show2.setDuration(7200L);
        show2.setTheatre(theatre1);
        show3.setId(3L);
        show3.setMovieName("movie2");
        show3.setScreenName("screen2");
        show3.setStartTime(1709032607000L);
        show3.setDuration(7200L);
        show3.setTheatre(theatre1);
    }

    @Test
    public void getAllTheatresTest() {
        Mockito.when(theatreRepository.findTheatreNameByCity("bengaluru")).thenReturn(List.of(theatre1,theatre2));
        List<Theatre> theatreList = theatreService.getAllTheatres("bengaluru");
        assertNotNull(theatreList);
        assertEquals(theatreList.size(),2);
        assertEquals(theatreList.get(0).getTheatreName(),"theatre2");
        assertEquals(theatreList.get(1).getTheatreName(),"theatre4");

    }

    @Test
    public void getAllMoviesByTheatreIdTest() {
        Mockito.when(showRepository.findByTheatre_Id(2L)).thenReturn(List.of(show2,show3));
        List<String> movieList = theatreService.getAllMoviesByTheatreId(2L);
        assertNotNull(movieList);
        assertEquals(movieList.size(),2);
        assertEquals(movieList.get(0),"movie1");
        assertEquals(movieList.get(1),"movie2");
    }
}
