package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.db.entities.Theatre;
import com.core.moviebookingportal.db.repository.ShowRepository;
import com.core.moviebookingportal.dto.AllShowResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ShowServiceTest {

    @InjectMocks
    private ShowService showService;
    @Mock
    private ShowRepository showRepository;

    private Shows show1 = new Shows();
    private Shows show2 = new Shows();
    private Shows show3 = new Shows();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        show1.setId(1L);
        show1.setMovieName("movie1");
        show1.setScreenName("screen1");
        show1.setStartTime(1709021807000L);
        show1.setDuration(7200L);
        Theatre theatre = new Theatre();
        theatre.setId(2L);
        theatre.setTheatreName("theatre1");
        theatre.setCity("bengaluru");
        show1.setTheatre(theatre);
        show2.setId(2L);
        show2.setMovieName("movie1");
        show2.setScreenName("screen1");
        show2.setStartTime(1709032607000L);
        show2.setDuration(7200L);
        show2.setTheatre(theatre);
        show3.setId(3L);
        show3.setMovieName("movie1");
        show3.setScreenName("screen2");
        show3.setStartTime(1709032607000L);
        show3.setDuration(7200L);
        show3.setTheatre(theatre);
    }

    @Test
    public void getAllShowsTest() {
        List<Shows> showsList = List.of(show1,show2,show3);
        Mockito.when(showRepository.findByTheatre_IdAndMovieName(2L,"movie1" )).thenReturn(showsList);
        List<AllShowResponseDto> allShowResponseDtoList =showService.getAllShows(2L,"movie1");
        assertNotNull(allShowResponseDtoList);
        assertEquals(allShowResponseDtoList.size(),3);
        assertEquals(allShowResponseDtoList.get(0).getId(),1L);
        assertEquals(allShowResponseDtoList.get(1).getId(),2L);
        assertEquals(allShowResponseDtoList.get(2).getId(),3L);

    }
}
