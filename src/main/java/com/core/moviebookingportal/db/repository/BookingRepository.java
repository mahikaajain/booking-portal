package com.core.moviebookingportal.db.repository;

import com.core.moviebookingportal.db.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Booking findByUserAndShowId(String userId, Long showId );

    List<Booking> findByShow_Id(Long showId);


}
