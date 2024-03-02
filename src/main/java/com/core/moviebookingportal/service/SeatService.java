package com.core.moviebookingportal.service;

import com.core.moviebookingportal.db.entities.Seat;
import com.core.moviebookingportal.db.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public List<Seat> getAllAvailableSeats(final Long showId) {
        return seatRepository.findByShow_IdAndIsBooked(showId,false);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteAllSeatsByShowId(final Long showId) {
         seatRepository.deleteAllByShow_Id(showId);
    }
}
