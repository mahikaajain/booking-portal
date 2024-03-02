package com.core.moviebookingportal.db.repository;

import com.core.moviebookingportal.db.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    Seat findBySeatNoAndShow_Id(String seatNo, Long showId);

    List<Seat> findByShow_IdAndIsBooked(Long showId, boolean isBooked);

    void deleteAllByShow_Id(Long showId);

}
