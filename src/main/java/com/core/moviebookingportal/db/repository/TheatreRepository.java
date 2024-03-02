package com.core.moviebookingportal.db.repository;

import com.core.moviebookingportal.db.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    @Query("Select DISTINCT t.city from Theatre t")
    List<String> findDistinctCity();

    List<Theatre> findTheatreNameByCity(String city);

}
