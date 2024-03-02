package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Shows;
import com.core.moviebookingportal.db.repository.ShowRepository;
import com.core.moviebookingportal.dto.AllShowResponseDto;
import com.core.moviebookingportal.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

@Autowired
    ShowRepository showRepository;

    public List<AllShowResponseDto> getAllShows(final Long theatreId, final String movieName) {
        List<Shows> showsList = showRepository.findByTheatre_IdAndMovieName(theatreId,movieName );
        List<AllShowResponseDto> allShowResponseDtoList = new ArrayList<>();
        for(Shows shows:showsList){
            AllShowResponseDto allShowResponseDto = new AllShowResponseDto(shows);
            allShowResponseDtoList.add(allShowResponseDto);
        }
        return allShowResponseDtoList;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public Shows deleteByShowId(final Long showId) throws Exception {
        Optional<Shows> show = showRepository.findById(showId);
        if(show.isEmpty()){
            throw new ApiException("show not found", HttpStatus.NOT_FOUND);
        }
        showRepository.deleteById(show.get().getId());
        return show.get();
    }

    @org.springframework.transaction.annotation.Transactional(isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public List<Shows> deleteMovieByTheatreId(final Long theatreId, final String movieName) throws Exception {
        List<Shows> shows= showRepository.findByTheatre_IdAndMovieName(theatreId,movieName);
        if(shows.isEmpty()){
            throw new ApiException("shows not found", HttpStatus.NOT_FOUND);
        }
        showRepository.deleteAll(shows);
        return shows;
    }


//    public Shows delete(){
//
//        if(show.startTime()+ duration<local.now){
//            List<Seats>;
//            for(Seat seat){
//                if(seat.isBooked true){
//                    throw exception
//                }
//            }
//        }else{
//            throw exception( cant delete)
//        }
//        return deleteByShowId(showId)
//    }
}
