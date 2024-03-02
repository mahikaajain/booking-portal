package com.core.moviebookingportal.db.repository;

import com.core.moviebookingportal.db.entities.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Shows, Long> {

    List<Shows> findByTheatre_Id(Long theatreId);

    List<Shows> findByTheatre_IdAndMovieName(Long theatreId, String movieName);


}
